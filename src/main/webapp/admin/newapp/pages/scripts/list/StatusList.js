/**
 * dictionary grid list
 */
Ext.define('Admin.list.StatusList', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.statuslist',
	title : 'Task Status List',
	header : false,
	forceFit : true,
	url : contextPath + '/status/listStatus',
	initComponent : function() {
		this.store = Ext.create('Ext.data.Store', {
			proxy : {
				type : 'ajax',
				url : this.url,
				reader : {
					type : 'json',
					rootProperty : 'recordlist',
					totalProperty : 'total'
				}
			},
			fields : [ 'id', 'description', 'fontColor', 'bgColor' ]
		});

		this.columns = [
				{
					header : '',
					xtype : 'actioncolumn',
					menuDisabled : true,
					align : 'center',
					width : 9,
					items : [ {
						icon : contextPath
								+ '/common/images/icons/page_white_delete.png',
						tooltip : 'Delete',
						scope : this,
						handler : this.doDelete
					} ]
				},
				{
					header : 'ID',
					dataIndex : 'id',
					renderer : function(v, meta, rec) {
						return rec.phantom ? '' : v;
					}
				},
				{
					header : 'Description',
					dataIndex : 'description'
				},
				{
					header : 'Preview Color',
					dataIndex : 'fontColor',
					renderer : function(value, metaData, record, rowIndex,
							colIndex, store, view) {
						metaData.tdStyle += "color:" + value + ";"
								+ "background:" + record.data.bgColor + ";";
						return "Preview";
					}
				} ]
		this.bbar = new Ext.PagingToolbar({
			pageSize : 20,
			store : this.store,
			displayInfo : true
		});
		this.callParent(arguments);
	},
	doDelete : function(grid, rowIndex, colIndex) {
		var id = grid.store.getAt(rowIndex).data.id;
		Ext.Msg.confirm("notice", "are you sure?", function(v) {
			if (v == 'yes') {
				Ext.Ajax.request({
					url : contextPath + '/status/deleteStatus',
					params : {
						id : id
					},
					success : function(response, option) {
						var obj = Ext.util.JSON.decode(response.responseText);
						grid.store.reload();
						Ext.Msg.alert("notice", obj.message);
					},
					failure : function() {
						Ext.Msg.alert("notice", "error!");
					}
				});
			}
		})
	},
	reload : function(params) {
		if (params) {
			Ext.apply(this.store.proxy.extraParams, params);
		}
		this.store.reload();
	}
});