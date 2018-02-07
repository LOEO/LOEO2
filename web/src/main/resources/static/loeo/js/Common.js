/**
 * Created by LT on 2016年2月24日14:16:45
 * COMMON全局對象封裝一些常用的工具函數
 */
var COMMON = (function (_) {
	_.hanZiReg = new RegExp("[\\u4E00-\\u9FFF]", "g");

	//把source中的屬性附加到target上
	_.extend = function (target, source, b) {
		if (source) {
			for (var k in source) {
				if (!b || !target.hasOwnProperty(k)) {
					target[k] = source[k];
				}
			}
		}
		return target;
	};

	// 返回一個繼承了parent的子類
	_.inherits = function (superClass, subClass) {
		subClass.prototype = _.extend(new superClass(), subClass.prototype);
		subClass.superCls = subClass.constructor;
		subClass.prototype.constructor = subClass;
		return subClass;
	};

	_.each = function (obj, iterator, context) {
		if (obj == null) return;
		if (obj.length === +obj.length) {
			for (var i = 0, l = obj.length; i < l; i++) {
				if (iterator.call(context, obj[i], i, obj) === false)
					return false;
			}
		} else {
			for (var key in obj) {
				if (obj.hasOwnProperty(key)) {
					if (iterator.call(context, obj[key], key, obj) === false)
						return false;
				}
			}
		}
	};

	_.str2json = function (s) {

		if (!_.isString(s)) return null;
		if (_.JSON) {
			return JSON.parse(s);
		} else {
			return (new Function("return " + (s && s.trim()) || ''))();
		}

	};
	_.json2str = (function () {

		if (window.JSON) {

			return JSON.stringify;

		} else {

			var escapeMap = {
				"\b": '\\b',
				"\t": '\\t',
				"\n": '\\n',
				"\f": '\\f',
				"\r": '\\r',
				'"': '\\"',
				"\\": '\\\\'
			};

			function encodeString(source) {
				if (/["\\\x00-\x1f]/.test(source)) {
					source = source.replace(
						/["\\\x00-\x1f]/g,
						function (match) {
							var c = escapeMap[match];
							if (c) {
								return c;
							}
							c = match.charCodeAt();
							return "\\u00"
								+ Math.floor(c / 16).toString(16)
								+ (c % 16).toString(16);
						});
				}
				return '"' + source + '"';
			}

			function encodeArray(source) {
				var result = ["["],
					l = source.length,
					preComma, i, item;

				for (i = 0; i < l; i++) {
					item = source[i];

					switch (typeof item) {
						case "undefined":
						case "function":
						case "unknown":
							break;
						default:
							if (preComma) {
								result.push(',');
							}
							result.push(_.json2str(item));
							preComma = 1;
					}
				}
				result.push("]");
				return result.join("");
			}

			function pad(source) {
				return source < 10 ? '0' + source : source;
			}

			function encodeDate(source) {
				return '"' + source.getFullYear() + "-"
					+ pad(source.getMonth() + 1) + "-"
					+ pad(source.getDate()) + "T"
					+ pad(source.getHours()) + ":"
					+ pad(source.getMinutes()) + ":"
					+ pad(source.getSeconds()) + '"';
			}

			return function (value) {
				switch (typeof value) {
					case 'undefined':
						return 'undefined';

					case 'number':
						return isFinite(value) ? String(value) : "null";

					case 'string':
						return encodeString(value);

					case 'boolean':
						return String(value);

					default:
						if (value === null) {
							return 'null';
						} else if (_.isArray(value)) {
							return encodeArray(value);
						} else if (_.isDate(value)) {
							return encodeDate(value);
						} else {
							var result = ['{'],
								encode = _.json2str,
								preComma,
								item;

							for (var key in value) {
								if (Object.prototype.hasOwnProperty.call(value, key)) {
									item = value[key];
									switch (typeof item) {
										case 'undefined':
										case 'unknown':
										case 'function':
											break;
										default:
											if (preComma) {
												result.push(',');
											}
											preComma = 1;
											result.push(encode(key) + ':' + encode(item));
									}
								}
							}
							result.push('}');
							return result.join('');
						}
				}
			};
		}

	})();

	//添加基本對象判斷函數
	_.each(['String', 'Function', 'Array', 'Number', 'RegExp', 'Object', 'Date'], function (v) {
		_['is' + v] = function (obj) {
			return Object.prototype.toString.apply(obj) == '[object ' + v + ']';
		}
	});

	//創建UUID
	_.createUUID = function () {
		function S4() {
			return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
		}

		return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
	}

	//得到查詢字符串
	_.getQueryString = function (name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg)
		return r != null ? unescape(r[2]) : null;
	};

	return _;
}({}));

/**
 * String
 */
String.prototype.trim = function () {
	return this.replace(/^\s*/g, '');
};


/**
 * Date
 */
Date.prototype.format = function (pattern) {
	var o = {
		"M+": this.getMonth() + 1,
		"d+": this.getDate(),
		"h+": this.getHours(),
		"m+": this.getMinutes(),
		"s+": this.getSeconds(),
		"q+": Math.floor((this.getMonth() + 3) / 3),
		"S": this.getMilliseconds()
	}
	if (/(y+)/.test(pattern)) {
		pattern = pattern.replace(RegExp.$1, this.getFullYear() + "").substr(4 - RegExp.$1.length)
	}
	for (var k in o)
		if (new RegExp("(" + k + ")").test(pattern))
			pattern = pattern.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00") + o[k]).substr(("" + o[k]).length));
	return pattern;
};