<%--
  Created by IntelliJ IDEA.
  User: LT
  Date: 2016/11/05
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${ctx}/rs/plugins/ztree/css/metroStyle/metroStyle.css"/>
<script src="${ctx}/rs/jquery-easyui-1.5/src/jquery.parser.js"></script>
<script type="text/javascript" src="${ctx}/rs/plugins/ztree/jquery.ztree.all.min.js"></script>
<section class="content box box-primary easyui-layout" style="height:100%">
	<table id="resourceGrid" class="easyui-treegrid" toolbar="#toolbar">
	</table>
	<div id="toolbar" class="tool-bar">
        <span class="tool-btn">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" id="addBtn" code="resource_add">新增</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="editBtn" code="resource_update">修改</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="delBtn" code="resource_delete">删除</a>
        </span>
		<span class="search-box">
            <input type="text" class="easyui-textbox" id="s_name"
				   data-options="label:'资源名:',labelWidth:50,width:200">
            <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconCls="icon-search"
			   id="searchBtn"></a>
        </span>
	</div>
	<div id="resourceWin" class="easyui-dialog" style="width:400px"
		 closed="true" buttons="#dlg-buttons">
		<form id="resourceForm" method="post" novalidate style="margin:0;padding:20px 50px">
			<input type="hidden" name="id">
			<input type="hidden" id="resourcePid" name="pid">
			<input type="hidden" id="resourceIsLeaf" name="isLeaf">
			<div style="margin-bottom:10px">
				<input id="name" name="name" class="easyui-textbox" required="true" label="资源名:"
					   style="width:94%">
			</div>
			<div style="margin-bottom:10px">
				<input id="resourceType" name="type" class="easyui-combobox" required="true" label="类型:"
					   panelHeight="auto"
					   style="width:94%"
					   data-options=""
				>
			</div>
			<div style="margin-bottom:10px;display:none;">
				<input id='resourceLink' name="link" class="easyui-textbox" label="链接:"
					   style="width:94%">
			</div>
			<div style="margin-bottom:10px">
				<input name="iconCls" class="easyui-textbox" label="图标:"
					   style="width:94%">
			</div>
			<div style="margin-bottom:10px">
				<input name="code" class="easyui-textbox" label="代码:"
					   style="width:94%">
			</div>
			<div style="margin-bottom:10px">
				<input name="orde" class="easyui-textbox" label="排序:"
					   style="width:94%">
			</div>
			<div style="margin-bottom:10px">
				<input id='enable' name="enable" class="easyui-combobox" required="true" label="状态:" panelHeight="auto"
					   style="width:94%"
					   data-options="value:1,editable:false,valueField: 'id',textField: 'text',data:[{id:'1',text:'启用'},{id:'0',text:'禁用'}]"
				>
			</div>
			<div style="margin-bottom:10px">
				<input name="descp" class="easyui-textbox" data-options="multiline:true" label="描述:"
					   style="width:94%">
			</div>
		</form>
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" id="saveResourceBtn"
			   style="width:90px">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			   onclick="javascript:$('#resourceWin').window('close')" style="width:90px">取消</a>
		</div>
	</div>
</section>
<script type="text/javascript">
	$(function () {
		var resourceGrid = LOEO.treeGrid("resourceGrid", {
			url: '${ctx}/resource/list.do',
			idField: 'id',
			treeField: 'name',
			useCustomIcon: true,
			checkbox: false,
			columns: [[
				{field: 'id', title: 'id', hidden: true, align: 'center'},
				{field: 'name', title: '资源名', align: 'center'},
				{field: 'link', title: '链接', width: 100, align: 'center'},
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
				{field: 'pid', title: '父id', align: 'center', width: 60},
				{
					field: 'iconCls', title: '图标', align: 'center', width: 60, formatter: function (value) {
						return '<i class="' + value + '"></i>';
					}
				},
				{field: 'code', title: '代码', align: 'center', width: 60},
				{field: 'orde', title: '排序', align: 'center', width: 60},
				{field: 'isLeaf', title: '叶子节点', align: 'center', width: 60},
				{
					field: 'enable', title: '状态', width: 80, align: 'center', formatter: function (value) {
						return LOEO.ENABLE[value];
					}
				},
				{field: 'descp', title: '描述', align: 'center', width: 60},
				{field: 'createUser', title: '创建人', align: 'center'},
				{field: 'createDt', title: '创建时间', align: 'center'},
				{field: 'updateUser', title: '修改人', align: 'center'},
				{field: 'updateDt', title: '修改时间', align: 'center'}
			]]
		});
		$("#searchBtn").on("click", function () {
			resourceGrid.datagrid("load", {
				username: $("#s_username").textbox('getValue')
			});
		});

		var resourceForm = LOEO.initForm("resourceForm", function (result) {
			if (result.state === LOEO.SUCCESS) {
				$('#resourceWin').window('close');
				resourceGrid.treegrid("reload");
			}
			LOEO.messager(result.msg);
		});
		$("#resourceType").combobox({
			editable: false,
			valueField: 'id',
			textField: 'text',
			data: [{id: '0', text: '目录'}, {id: '1', text: '菜单'}, {id: '2', text: '按钮'}],
			onSelect: function (selected) {
				debugger;
				if (selected.id !== "0") {
					$("#resourceLink").textbox({
						required: true
					}).parent().show().find("#resourceLink + span").width(187).find("input").width(187);
				} else {
					$("#resourceLink").hide().textbox({
						required: false
					}).parent().hide();
				}
			}
		});
		$("#addBtn").on("click", function () {
			var row = resourceGrid.datagrid("getSelected");
			if (row && row.type === "2") {
				LOEO.messager("按钮下不能添加子节点！");
				return;
			}
			LOEO.openFormWin("resourceWin", {
				title: "新增资源"
			}, function () {
				debugger;
				$("#resourceType").combobox("clear");
				resourceForm.form("clear").form({url: "${ctx}/resource/add.do"});
				$("#resourceLink").parent().hide();
				var row = resourceGrid.treegrid("getSelected");
				if (row) {
					$("#resourcePid").val(row.id);
				} else {
					$("#resourcePid").val(0);
				}
				$("#enable").combobox("setValue", "1");
			});
		});
		$("#editBtn").on("click", function () {
			var row = resourceGrid.datagrid("getSelected");
			if (row) {
				resourceForm.form("load", row).form({url: "${ctx}/resource/edit.do"});
				$("#username").textbox({editable: false});
				$("#password").textbox("setValue", "");
				$("#password1").textbox("setValue", "");
				LOEO.openFormWin("resourceWin", {
					title: "修改资源"
				});
			} else {
				LOEO.messager("请选择一行数据！");
			}
		});

		$("#saveResourceBtn").on("click", function () {
			resourceForm.form("submit");
		});

		$("#delBtn").on("click", function () {
			debugger;
			var row = resourceGrid.datagrid("getSelected");
			if (row.isLeaf == 0) {
				LOEO.messager("非叶子节点不能删除！");
				return;
			}
			if (row) {
				$.messager.confirm('提示', '确定要删除吗?', function (r) {
					if (r) {
						$.post("${ctx}/resource/delete.do", {
							id: row.id,
							pid: row.pid
						}, function (result) {
							debugger;
							if (result.state === LOEO.SUCCESS) {
								resourceGrid.treegrid("reload");
							}
							LOEO.messager(result.msg);
						});

					}
				});
			} else {
				LOEO.messager("请选择一行数据！");
			}
		});
	});
</script>