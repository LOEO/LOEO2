<%--
  Created by IntelliJ IDEA.
  User: LT
  Date: 14-4-2
  Time: 下午6:22
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${ctx}/rs/plugins/ztree/css/metroStyle/metroStyle.css"/>
<script src="${ctx}/rs/jquery-easyui-1.5/src/jquery.parser.js"></script>
<script type="text/javascript" src="${ctx}/rs/plugins/ztree/jquery.ztree.all.min.js"></script>
<section class="content box box-primary easyui-layout" style="height:100%">
	<div data-options="region:'west',split:false,collapsible:false" style="width:70%">
		<div class="easyui-tabs" style="width:100%;height:100%" id="tabs">
			<div title="角色" style="padding:0px" data-options="master:'role'">
				<table class="easyui-datagrid" id="roleGrid" toolbar="#toolbar">
				</table>
			</div>
			<div title="组织" style="padding:0px" data-options="master:'org'">
				<table class="easyui-datagrid" id="orgGrid" toolbar="#buttonToolbar">
				</table>
			</div>
			<div title="用户" style="padding:0px" data-options="master:'user'">
				<table class="easyui-datagrid" id="userGrid" toolbar="#buttonToolbar">
				</table>
			</div>
		</div>
	</div>
	<div data-options="region:'center',title:'授权管理'" style="width:30%">
		<table id="resourceGrid" class="easyui-treegrid" toolbar="#resourceToolbar">
		</table>
		<div id="resourceToolbar" class="tool-bar">
        <span class="tool-btn">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true"
			   id="saveResourceBtn">保存</a>
        </span>
		</div>
	</div>
</section>
<script type="text/javascript">
	$(function () {
		var roleGrid = LOEO.initGrid("roleGrid", {
				url: '${ctx}/role/list.do',
				fit: true,
				pageSize: 30,
				columns: [[
					{field: 'id', title: 'id', hidden: true, width: 80, align: 'center'},
					{field: 'name', title: '角色名', width: 80, align: 'center'},
					{field: 'code', title: '角色代码', width: 100, align: 'center'},
					{field: 'descp', title: '描述', align: 'center', width: 60},
					{
						field: 'enable', title: '状态', width: 80, align: 'center', formatter: function (value) {
							return LOEO.ENABLE[value];
						}
					}
				]],
				onSelect: function (index, row) {
					loadResource({
						master: "role",
						masterValue: row.id
					});
				}
			}),
			userGrid = LOEO.initGrid("userGrid", {
				url: '${ctx}/user/list.do',
				fit: true,
				columns: [[
					{field: 'id', title: 'id', hidden: true, width: 80, align: 'center'},
					{field: 'username', title: '用户名', width: 80, align: 'center'},
					{field: 'nickname', title: '姓名', width: 100, align: 'center'},
					{field: 'age', title: '年龄', align: 'center', width: 60},
					{
						field: 'enable', title: '状态', width: 80, align: 'center', formatter: function (value) {
							return LOEO.ENABLE[value];
						}
					}
				]],
				onSelect: function (index, row) {
					loadResource({
						master: "user",
						masterValue: row.id
					});
				}
			});
		var menuTree, curTreeNode, treeSetting = {
			view: {
				dblClickExpand: false,
				showLine: true,
				selectedMulti: false
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pid",
					rootPId: "0"
				},
				key: {
					name: "name"
				}
			},
			callback: {
				onClick: function (event, treeId, treeNode) {
					curTreeNode = treeNode;
				}
			},
			check: {
				enable: true
			}
		}, loadResource = function (params) {
			debugger;
			var roots = resourceGrid.treegrid("getRoots");
			for (var i = 0, len = roots.length; i < len; i++) {
				resourceGrid.treegrid("uncheckNode", roots[i].id);
			}
			resourceGrid.treegrid("load", params);
			/*$.post("
			${ctx}/privilege/menuAndButtonList.do", params,function (data) {
                debugger;
                $.each(data, function (i, o) {
                    if (o.type === "button") {
                        o.id = "button_" + o.id;
                        o.name = o.name + "(按钮)";
                    }
                });
                menuTree = $.fn.zTree.init($("#menuTree"), treeSetting, data);
                menuTree.expandAll(true);
            });*/
		};
		var resourceGrid = LOEO.treeGrid("resourceGrid", {
			url: '${ctx}/privilege/getResource.do',
			idField: 'id',
			treeField: 'name',
			useCustomIcon: true,
			columns: [[
				{field: 'id', title: 'id', hidden: true, align: 'center'},
				{field: 'name', title: '资源名', align: 'center'},
				{
					field: 'type', title: '类型', align: 'center', width: 60, formatter: function (value) {
						switch (value) {
							case "0":
								return "目录";
							case "1":
								return "菜单";
							case "2":
								return "按钮";
						}
					}
				},
				{
					field: 'enable', title: '状态', width: 80, align: 'center', formatter: function (value) {
						return LOEO.ENABLE[value];
					}
				}
			]]
		});
		$("#savePrivilegeBtn").on("click", function () {
			debugger;
			var curGrid, nodes = menuTree.getCheckedNodes(),
				master = $("#tabs").tabs("getSelected").panel('options').master,
				params = {
					nodes: COMMON.json2str(nodes).replace(/button_/g, ""),
					master: master
				};
			if (master === "role") {
				curGrid = roleGrid;
			} else if (master === "user") {
				curGrid = userGrid;
			}
			params.masterValue = curGrid.datagrid("getSelected").id;
			$.post("${ctx}/privilege/save.do", params, function (result) {
				LOEO.messager(result.msg);
			});

		});
		$("#searchBtn").on("click", function () {
			roleGrid.datagrid("load", {
				name: $("#s_name").textbox('getValue')
			});
		});

		$("#saveResourceBtn").on("click", function () {
			debugger;
			var curGrid, row, nodes, opt = $("#tabs").tabs("getSelected").panel('options');
			if (opt.master === "role") {
				curGrid = roleGrid;
			} else if (opt.master === "user") {
				curGrid = userGrid;
			}
			row = curGrid.datagrid("getSelected");
			if (row) {
				nodes = resourceGrid.treegrid("getCheckedNodes", "indeterminate").concat(resourceGrid.treegrid("getCheckedNodes"));
				$.post("${ctx}/privilege/save.do", {
					master: opt.master,
					masterValue: row.id,
					nodes: COMMON.json2str(nodes)
				}, function (result) {
					LOEO.messager(result.msg);
				});
			} else {
				LOEO.messager("请选择" + opt.title + "!");
			}
		});
	});
</script>

