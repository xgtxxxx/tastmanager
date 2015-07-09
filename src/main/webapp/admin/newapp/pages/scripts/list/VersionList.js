/**
 * dictionary grid list
 */
Ext.define('Admin.list.VersionList', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.versionlist',
	title : 'Task Status List',
	header : false,
	forceFit : true,
	url : contextPath + '/version/listVersion',
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
			fields : [ 'id', 'name', 'active' ]
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
				}, {
					header : 'ID',
					dataIndex : 'id',
					renderer : function(v, meta, rec) {
						return rec.phantom ? '' : v;
					}
				}, {
					header : 'Name',
					dataIndex : 'name'
				}, {
					header : 'Status',
					dataIndex : 'active',
					renderer : function(v) {
						if (v == 3) {
							return 'For all members';
						} else if (v == 2) {
							return "<font color='red'>New add</font>";
						}
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
		Ext.Msg.confirm("Notice", "Are you sure?", function(v) {
			if (v == 'yes') {
				Ext.Ajax.request({
					url : contextPath + '/version/deleteVersion',
					params : {
						id : id
					},
					success : function(response, option) {
						var obj = Ext.util.JSON.decode(response.responseText);
						grid.store.reload();
						Ext.Msg.alert("Notice", obj.message);
					},
					failure : function() {
						Ext.Msg.alert("Notice", "Error!");
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