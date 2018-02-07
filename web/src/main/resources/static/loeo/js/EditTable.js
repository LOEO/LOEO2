/**
 * Created by LT on 2016/2/24.
 */
//依賴 Common.js 和 easyui
COMMON.Table = function () {
	this.datagrid = $("<table></table>");
	this.options = {
		scrollbarSize: 0,
		fitColumns: true,
		nowrap: false,
		striped: true,
		remoteSort: false,
		singleSelect: true,
		rownumbers: true,
		pagination: true,
		pageSize: 10,
		pageNumber: 1,
		pageList: [10, 20, 30]
	};
	this.editIndex = undefined;
};

COMMON.Table.newInstance = function (id, subCls, superCls) {
	var sub = COMMON.inherits(superCls || this, subCls),
		instance = new sub();
	instance.datagrid = id ? $("#" + id) : this.datagrid;
	return instance;
};

COMMON.Table.prototype = {
	init: function () {
		this.datagrid.datagrid(this.options);
		return this;
	},
	getSelected: function () {
		var row = this.datagrid.datagrid("getSelected");
		return this.datagrid.datagrid("getSelected");
	},
	addRow: function () {
		this.datagrid.datagrid("appendRow");
		return this;
	},
	endEditing: function () {
		var dg = this.datagrid, editIndex = this.editIndex;
		if (editIndex == undefined) {
			return true
		}
		if (dg.datagrid('validateRow', editIndex)) {
			dg.datagrid('endEdit', editIndex);
			this.editIndex = undefined;
			return true;
		} else {
			return false;
		}
	},
	append: function (row) {
		row = row || {};
		var dg = this.datagrid;
		if (this.endEditing()) {
			dg.datagrid('appendRow', row);
			this.editIndex = dg.datagrid('getRows').length - 1;
			dg.datagrid('selectRow', this.editIndex)
				.datagrid('beginEdit', this.editIndex);
		}
		return this;
	},
	removeRow: function (rowIndex) {
		var dg = this.datagrid, editIndex = this.editIndex;
		if (rowIndex && rowIndex === editIndex) {
			this.editIndex = undefined;
		}
		dg.datagrid('deleteRow', rowIndex);
		return this;
	},
	accept: function () {
		var dg = this.datagrid;
		if (this.endEditing()) {
			dg.datagrid('acceptChanges');
		}
		return this;
	},
	reject: function () {
		var dg = this.datagrid;
		dg.datagrid('rejectChanges');
		this.editIndex = undefined;
		return this;
	},
	getChanges: function () {
		var rows = this.datagrid.datagrid('getChanges');
		alert(rows.length + ' rows are changed!');
		return this;
	},
	beginEdit: function (index) {
		var dg = this.datagrid, editIndex = this.editIndex;
		if (editIndex != index) {
			if (this.endEditing()) {
				dg.datagrid('selectRow', index)
					.datagrid('beginEdit', index);
				this.editIndex = index;
			} else {
				dg.datagrid('selectRow', editIndex);
			}
		}
		return this;
	},
	selectRow: function (index) {
		this.datagrid.datagrid("selectRow", index);
		return this;
	},
	getRows: function () {
		return this.datagrid.datagrid("getRows");
	},
	loadData: function (data) {
		this.datagrid.datagrid("loadData", data);
		return this;
	}
};