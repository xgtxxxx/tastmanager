Ext
		.define(
				'Admin.list.RoleList',
				{
					extend : 'Ext.grid.Panel',
					alias : 'widget.rolelist',
					title : 'Role List',
					header : false,
					hasOperate : true,
					forceFit : true,
					hasPageBar : true,
					url : contextPath + '/role',
					params : {},
					initComponent : function() {
						Ext.define('RoleModel', {
							extend : 'Ext.data.Model',
							fields : [ 'id', 'name' ]
						});
						this.store = Ext.create('Ext.data.Store', {
							model : 'RoleModel',
							proxy : {
								type : 'ajax',
								url : this.url,
								reader : {
									type : 'json',
									rootProperty : 'recordlist',
									totalProperty : 'total'
								}
							}
						// ,
						// autoLoad: true
						});

						this.columns = [
								{
									header : '',
									xtype : 'actioncolumn',
									align : 'center',
									menuDisabled : true,
									width : 9,
									handler : !this.hasOperate,
									items : this.hasOperate ? [ {
										icon : contextPath
												+ '/common/images/icons/page_white_delete.png',
										tooltip : 'Delete',
										scope : this,
										handler : this.doDelete
									} ]
											: []
								},
								{
									header : '',
									xtype : 'actioncolumn',
									align : 'center',
									menuDisabled : true,
									width : 9,
									handler : !this.hasOperate,
									items : this.hasOperate ? [ {
										icon : contextPath
												+ '/common/images/icons/page_white_magnify.png',
										tooltip : 'User',
										scope : this,
										handler : this.showUserSetWin
									} ]
											: []
								}, {
									header : 'ID',
									dataIndex : 'id',
									width : 120,
									renderer : function(v, meta, rec) {
										return rec.phantom ? '' : v;
									}
								}, {
									header : 'Name',
									dataIndex : 'name',
									width : 120
								} ]
						this.bbar = this.hasPageBar ? new Ext.PagingToolbar({
							pageSize : 20,
							store : this.store,
							displayInfo : true
						}) : "";
						this.callParent(arguments);
					},
					doDelete : function(grid, rowIndex, colIndex) {
						var id = grid.store.getAt(rowIndex).data.id;
						Ext.Msg.confirm("notice", "are you sure?", function(v) {
							if (v == 'yes') {
								Ext.Ajax.request({
									url : contextPath + '/role/deleteRole',
									params : {
										id : id
									},
									success : function(response, option) {
										var obj = Ext.util.JSON
												.decode(response.responseText);
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
					showUserSetWin : function(grid, rowIndex, colIndex) {
						var params = {
							roleId : grid.store.getAt(rowIndex).data.id
						}
						var win = Ext.create("Admin.win.UserWin", {
							params : params,
							type : 'Role'
						});
						win.show();
						win.reloadOther(params);
						win.reloadMine(params);
					},
					reload : function(params) {
						if (params) {
							Ext.apply(this.store.proxy.extraParams, params);
						}
						this.store.reload();
					}
				});