/**
 * Created by LT on 2016/2/24.
 * easyui 擴展
 */

//擴展驗證功能
$.extend($.fn.validatebox.defaults.rules, {
	minLength: {
		validator: function (value, param) {
			return value.length >= param[0];
		},
		message: '最少不能小于{0}位。。.'
	},
	equals: {
		validator: function (value, param) {
			return value == $(param[0]).val();
		},
		message: '{1}'
	},
	num: {
		validator: function (value) {
			return /^[1-9]\d*(\.\d+)?$/.test(value);

		},
		message: "只能輸入正整數,若輸入小數則<b style='color:red'>四捨五入</b>！"
	},
	notNull: {
		validator: function (value) {
			return value.trim() != "";
		},
		message: "該項為必輸項"
	},
	maxLength: {
		validator: function (value, params) {
			var temp = value.replace(/[\\u4E00-\\u9FFF]\g/, "");
			return temp.length + (value.length - temp.length) * 3 <= params[0];
			/*debugger;
			 return value.length<=params[0];*/
		},
		message: "輸入字數不能大於<b style='color:red'>{0}</b>個！"
		/*   message:"輸入字符個數不能大於<b style='color:red'>{0}</b>個！"*/
	},
	numRange: {
		validator: function (value, params) {
			debugger;
			var result = false;
			if (params.length == 3) {
				if (params[2] === 0) {
					if (value.indexOf(".") != -1) {
						params[2] = "只能輸入整數!";
						return false;
					}
				}
				params.pop();
			}
			if (params.length == 2) {
				result = value - 0 >= params[0] && value - 0 <= params[1];
				params.push("輸入數字範圍應該在<b style='color:red'>" + params[0] + "-" + params[1] + "</b>之間!");
			} else if (params.length == 1) {
				result = value - 0 <= params[0];
				params.push("");
				params.push("輸入數字不能大於<b style='color:red'>" + params[0] + "</b>！");
			} else {
				params.push("");
				params.push("");
				params.push("");
				params[2] = "驗證規則錯誤！";
			}
			return result;
		},
		message: "{2}"
	},
	invalidChar: {
		validator: function (value) {
			//[`~!#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）&mdash;—|{}【】‘；：”“'。，、？]
			var pattern = new RegExp("[`~!#$%^&*()=|{}':;',\\[\\]<>/?~！#￥……&*（）—|{}【】‘；：”“'。，、？]");
			return !pattern.test(value);
		},
		message: "不能包含<b style='color:red'>~!#$%￥^&*()=|</b>等特殊字符！"
	},
	fMail: {
		validator: function (value) {
			return /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@mail.foxconn.com$/i.test(value);
		},
		message: "請輸入正確的郵箱地址，必須以<b style='color:red'>@mail.foxconn.com</b>結尾！"
	},
	oneToHundred: {
		validator: function (value) {
			return /^(?:0|[1-9][0-9]?|100)$/.test(value);
		},
		message: "只能输入0~100正整数"
	},
	phone: {
		validator: function (value) {
			return /(^(\d{2,4}(-|\+))?\d{5,8})$|(13[0-9]{9})$/.test(value);
		},
		message: "請輸入正確的電話號碼！"
	}
});

// combo 擴展 clearBtn 屬性顯示清除按鈕
(function ($) {
	var cache = $.fn.combo,
		cacheProperty = $.extend(true, {}, $.fn.combo);

	function hideIcon() {
		cache.apply(this, ['getIcon', 0]).css('visibility',
			'hidden')
	}

	function showIcon() {
		cache.apply(this, ['getIcon', 0]).css('visibility',
			'visible')
	}

	$.fn.combo = function (options, param) {
		if (typeof options === "string") {
			return cache.apply(this, [options, param]);
		}
		options = options || {};
		return $.each(this, function () {
			var result, me = $(this);
			result = cache.apply(me, [options, param]);
			$.each(me, function () {
				var temp = $.data(this, "combo"),
					isCreate = false;
				if (temp && temp.options && temp.options.clearBtn) {
					cache.call($(this), {
						icons: [{
							iconCls: 'icon-clear',
							handler: function () {
								cache.call(me, "clear");
								if (options.afterClear) {
									options.afterClear.call($(this));
								}
							}
						}],
						onChange: function () {
							if (cache.call($(this), "getValue") !== "") {
								showIcon.call($(this));
							} else {
								hideIcon.call($(this));
							}
						}
					});
					isCreate = true;
				}
				isCreate && (hideIcon.call($(this)), isCreate = false);
			});
			return result;
		});
	};
	$.extend($.fn.combo, cacheProperty);
	$.extend($.fn.combo.defaults, {clearBtn: false});
})(jQuery);

//解?easyui datebox 不能?月（覆?默?parser）
$.extend($.fn.datebox.defaults, {
	parser: function (value) {
		if ($.isNumeric(value)) {
			var date = new Date();
			date.setTime((value - 0));
			return date;
		}
		if (!value) return new Date();
		var value = value.split('-');
		var y = parseInt(value[0], 10);
		var m = parseInt(value[1], 10);
		var d = parseInt(value[2], 10);
		if (!isNaN(y) && !isNaN(m)) {
			if (isNaN(d)) {
				d = 1;
			}
			return new Date(y, m - 1, d);
		} else {
			return new Date();
		}
	}
});

//?展datagrid刷新?前?
$.extend($.fn.datagrid.methods, {
	refreshCurPage: function (grid) {
		var page = grid.datagrid('getPager');
		page.pagination("select", page.pageNumber);
	}
});

//解決combobox 額地歎了設置為false后  驗證提示彈不出來  2016年4月28日14:21:36
$.fn.validatebox.defaults.events.focus = function (e) {
	var target = e.data.target;
	var validateBox = $.data(target, "validatebox");
	var $validateBox = $(target);
	validateBox.validating = true;
	validateBox.value = undefined;
	(function () {
		if (validateBox.validating) {
			if (validateBox.value != $validateBox.val()) {
				validateBox.value = $validateBox.val();
				if (validateBox.timer) {
					clearTimeout(validateBox.timer);
				}
				validateBox.timer = setTimeout(function () {
					$(target).validatebox("validate");
				}, validateBox.options.delay);
			} else {
				_f(target);
			}
			setTimeout(arguments.callee, 200);
		}
	})();

	function _f(target) {
		var vb = $.data(target, "validatebox");
		if (vb && vb.tip) {
			$(target).tooltip("reposition");
		}
	};
};

// datebox 擴展showMonth屬性，只顯示月面板
!(function ($) {
	var cache = $.fn.datebox, cacheProperty = $.extend(true, {}, $.fn.datebox);
	$.fn.datebox = function (options, param) {
		if (typeof options === "string") {
			return cache.apply(this, [options, param]);
		}
		return $.each(this, function () {
			var me = $(this),
				p = undefined, //日期???象
				span = undefined, //?示月份?的触?控件;
				tds = false,//日期???象中月份
				temp = $.extend({}, cacheProperty.parseOptions(me[0]));
			if (temp.showMonth || (options && options.showMonth)) {
				$.extend(temp, {
					onShowPanel: function () {//?示日????象后再触??出月份?的事件，初始化??有生成月份?
						p = p || cache.call(me, 'panel');
						span = span || p.find('span.calendar-text');
						span.trigger('click'); //触?click事件?出月份?
						if (!tds) setTimeout(function () {//延?触??取月份?象，因?上面的事件触?和?象生成有???隔
							tds = p.find('div.calendar-menu-month-inner td');
							tds.click(function (e) {
								e.stopPropagation(); //禁止冒泡?行easyui?月份?定的事件
								var year = /\d{4}/.exec(span.html())[0]//得到年份
									, month = parseInt($(this).attr('abbr'), 10); //月份
								cache.call(me, 'hidePanel')//?藏日期?象
									.datebox('setValue', year + '-' + month);//?置日期的值
							});
						}, 0);
					},
					formatter: function (date) {
						if (date == null || date == "" || date == undefined) {
							return "";
						} else {
							return date.format("yyyy-MM");
						}
					}
				});
			}
			return cache.apply(me, [$.extend(temp, options || {}), param]);
		});

	};
	$.extend($.fn.datebox, cacheProperty);
})(jQuery);
