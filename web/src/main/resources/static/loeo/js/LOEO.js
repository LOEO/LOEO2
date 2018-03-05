/**
 * Created by LT on 2016/7/3 0003 20:26
 */
var LOEO = LOEO || {};
LOEO.SUCCESS = "SUCCESS";
LOEO.FAILED = "FAILED";
LOEO.DATE_PATTERN = "yyyy-MM-dd hh:mm:ss";
LOEO.ENABLE = {"0": "禁用", "1": "启用"};
LOEO.DataTable = function (id, options) {
	debugger;
	options = $.extend(true, {
		"select": "single",
		"pagingType": "simple_numbers",
		"processing": true,
		"serverSide": true,
		"lengthChange": false,
		"searching": false,
		"ordering": true,
		"info": true,
		"autoWidth": true,
		"ajax": {
			type: "post",//后台指定了方式，默认get，外加datatable默认构造的参数很长，有可能超过get的最大长度。
			url: "${ctx}/userAjax/user_list.do",
			dataSrc: "rows",//默认data，也可以写其他的，格式化table的时候取里面的数据
			data: function (d) {//d 是原始的发送给服务器的数据，默认很长。
				debugger;
				var param = {};//因为服务端排序，可以新建一个参数对象
				param.start = d.start;//开始的序号
				param.limit = d.length;//要取的数据的
				param.order = d.ordering;
				return param;//自定义需要传递的参数。
			}
		},
		language: {
			"lengthMenu": "每页显示 _MENU_ 条记录",
			"zeroRecords": "对不起，没有匹配的数据",
			"info": "第<b> _START_ - _END_</b> 条 / 共<b>_TOTAL_</b> 条数据",
			"infoEmpty": "没有匹配的数据",
			"infoFiltered": "(数据表中共 _MAX_ 条记录)",
			"processing": "正在加载中...",
			"paginate": {
				"first": "首页",
				"last": "尾页",
				"next": "下一页",
				"previous": "上一页"
			}
		}
	}, options), $dt = $("#" + id), dataTable = $dt.DataTable(options);
	if (options.select === "single") {
		dataTable.on('click', 'tr', function () {
			$("tr", $dt).removeClass("info");
			$(this).addClass('info');
		});
	} else if (options.select === "multi") {
		dataTable.on('click', 'tr', function () {
			$(this).toggleClass('info');
		});
	}
	return {
		dt: dataTable,
		getSelected: function () {
			return dataTable.row($(".info", $dt)).data();
		},
		reload: function () {
			dataTable.ajax.reload();
		},
		refresh: function () {
			dataTable.ajax.reload(null, false);
		}
	};
};
LOEO.ResultState = {
	SUCCESS: "SUCCESS",
	FAILED: "FAILED"
};
LOEO.validator = {
	excluded: [':disabled'],
	message: 'This value is not valid',
	feedbackIcons: {
		valid: 'glyphicon glyphicon-ok',
		invalid: 'glyphicon glyphicon-remove',
		validating: 'glyphicon glyphicon-refresh'
	},
	fields: {
		notnull: {
			selector: '.loeo-notnull',
			validators: {
				notEmpty: {
					message: '必填项！'
				}
			}
		},
		integer: {
			selector: '.loeo-selector',
			validators: {
				integer: {
					message: '请输入整数！'
				}
			}
		},
		emailAddress: {
			selector: '.loeo-email',
			validators: {
				emailAddress: {
					message: '请输入正确的邮箱！'
				}
			}
		},
		phone: {
			selector: '.loeo-phone',
			validators: {
				phone: {
					message: '请输入正确的电话！'
				}
			}
		}
	}
};

LOEO.validatorParser = {
	defaultOptions: {
		excluded: [':disabled'],
		message: 'This value is not valid',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		}
	},
	validators: {
		notnull: {
			notEmpty: {
				message: '必填项！'
			}
		},
		integer: {
			integer: {
				message: '请输入整数！'
			}
		}
	},
	parseOption: function ($field) {
		debugger;
		var me = this, name = $field.attr("name"),
			option = $field.attr("validType"),
			validator, result = {};
		if (!option) return;
		try {
			option = JSON.parse(option);
			if ($.isArray(option)) {
				validator = {validators: {}};
				$.each(option, function (i, o) {
					$.extend(validator.validators, me.validators[option]);
				});
			}
		} catch (e) {
			validator = {validators: me.validators[option]};
		}
		result[name] = validator;
		return result;
	},
	getValidator: function ($form) {
		debugger;
		var me = this, validators = {};
		$.each($form[0], function (i, o) {
			$.extend(validators, me.parseOption($(o)) || {});
		});
		var a = $.extend(true, {}, this.defaultOptions, {fields: validators});
		return $.extend(true, {}, this.defaultOptions, {fields: validators});
	}
};
/*LOEO.initForm=function(options) {
    debugger;
    var $form = $("#"+options.id);
    $form.bootstrapValidator($.extend({}, LOEO.validatorParser.getValidator($form), options.validators || {}));
    var validator = $form.data('bootstrapValidator'),
        form = {
        submit:function() {
            validator.validate();
            debugger;
            if(validator.isValid()){
                $.post(options.url||$form.attr("action"), $form.serializeArray(), function (data) {
                    options.success&& $.isFunction(options.success)&&options.success.call(this,data);
                });
            }
        },
        reset:function() {
            validator.resetForm(true);
            $form.each(function(){
                if($(this).is("form")){
                    this.reset();
                    $("[type=hidden]", this).val("");
                }
            })
        },
        load:function(data) {
            $form.each(function(){
                if($(this).is("form")){
                    $.each(this,function() {
                        $(this).val(data[this.name]);
                    });
                }
            })
        }
    };
    return form;
};*/

$.extend($.fn.datepicker.defaults, {autoclose: true});
//easyui
LOEO.initGrid = function (id, options) {
	return $("#" + id).datagrid($.extend({}, {
		fitColumns: true,
		striped: true,
		nowrap: true,
		loadMsg: '正在加载数据，请稍候...',
		emptyMsg: '无数据',
		pagination: true,
		rownumbers: true,
		singleSelect: true,
		ctrlSelect: true,
		checkOnSelect: true,
		selectOnCheck: true,
		pagePosition: 'bottom',
		pageList: [10, 20, 30],
		scrollbarSize: 0,
		loadFilter:function(result) {
			if(result && result.success){
				return {
					pageNO:result.data.current,
					pageSize:result.data.size,
					total:result.data.total,
					rows:result.data.records
				}
			}
			return {};
		}
	}, options || {}));
};

LOEO.openFormWin = function (id, opt, callback) {
	var win = $("#" + id).window($.extend({
		modal: true,
		top: 100,
		/*        top: 50,
				left: 100,*/
		onOpen: function () {
			//W.switchEnableBlur($(this).parent().find(".panel-tool"));
		}
	}, opt || {}));
	callback && $.isFunction(callback) && callback();
	win.slideDown();
	win.window("open");
};

LOEO.initForm = function (id, callback) {
	return $('#' + id).form({
		onSubmit: function () {
			$.messager.progress({
				interval: 100,
				text: "正在保存..."
			});
			var isValid = $(this).form('validate'),
				$tbs = $(".easyui-textbox", this);
			if (!isValid) {
				/* $("#crsTypes").combobox('textbox').trigger('mouseover');
				 $("#crsNames").combobox('textbox').trigger('mouseover');*/
				$.messager.progress('close');
			}
			$tbs.each(function () {
				$(this).textbox("setValue", $.trim($(this).textbox("getValue")));
			});
			return isValid;
		},
		success: function (result) {
			debugger;
			if (result.indexOf('<head page="login">') != -1) {
				window.location.href = "../pages/common/system/login.jsp"
			}
			$.messager.progress('close');
			callback && $.isFunction(callback) && callback(COMMON.str2json(result));
		}
	});
};

LOEO.del = function (url, data, callback) {
	$.messager.confirm("系統提示", "你確定要刪除嗎？", function (r) {
		if (r) {
			$.post(url, data, function (result) {
				$.messager.progress({
					interval: 100,
					text: "正在刪除..."
				});
				callback && $.isFunction(callback) && callback(result);
				$.messager.progress('close');
			})
		}
	});
};

LOEO.messager = function (msg) {
	$.messager.show({
		title: '系统提示',
		msg: "<b style='color:red'>" + msg + "</b>",
		showType: 'show'
	});
};

LOEO.treeGrid = function (id, options) {
	var onLoadSuccess = options.onLoadSuccess || function () {
		},
		onExpand = options.onExpand || function () {
		};
	return $("#" + id).treegrid($.extend({}, {
		fitColumns: true,
		striped: true,
		nowrap: true,
		loadMsg: '正在加载数据，请稍候...',
		emptyMsg: '无数据',
		pagination: false,
		rownumbers: true,
		singleSelect: true,
		ctrlSelect: true,
		checkOnSelect: true,
		selectOnCheck: true,
		checkbox: true,
		scrollbarSize: 0,
		loadFilter:function(result) {
			if(result && result.success){
				return result.data;
			}
			return {};
		}
	}, options, {
		onLoadSuccess: function (row, data) {
			if (options.useCustomIcon) {
				$(".tree-icon,.tree-file").removeClass("tree-icon tree-file").css("line-height", "18px");
				$(".tree-icon,.tree-folder").removeClass("tree-icon tree-folder tree-folder-open tree-folder-closed").css("line-height", "18px");
			}
			onLoadSuccess(row, data);
		},
		onExpand: function (row) {
			debugger;
			if (options.useCustomIcon) {
				$(".tree-icon,.tree-file").removeClass("tree-icon tree-file").css("line-height", "18px");
				$(".tree-icon,.tree-folder,.tree-folder-open").removeClass("tree-icon tree-folder tree-folder-open tree-folder-closed").css("line-height", "18px");
			}
			onExpand(row);
		}
	}));
};

/*
(function ($) {
    var cache= $.ajax;
    $.ajax=function() {
        debugger;
        var args = [].slice.apply(arguments),
            success,complete,
            successWrapper=function(data, textStatus, jqXHR){
                redirect(jqXHR, success, [data, textStatus, jqXHR]);
            },
            completeWrapper=function(jqXHR, textStatus) {
                redirect(jqXHR, complete, [jqXHR, textStatus]);
            },
            redirect=function(jqXHR,callback,args) {
                if(jqXHR.responseText.indexOf('<head page="login">')!=-1) {
                    $('body').html(jqXHR.responseText);
                }else{
                    callback.apply($,args);
                }
            };
        if(args.length===1&& args[0]) {
            success = args[0].success|| function(){};
            args[0].success = successWrapper;
            complete = args[0].complete = function () {};
            args[0].complete = completeWrapper;
        }
        cache.apply(this, args);
    };
})(jQuery);*/

