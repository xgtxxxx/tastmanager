Ext.define('Admin.view.TeamUser', {
	extend : 'Ext.window.Window',
	alias : 'widget.teamuser',

	modal : true,
	layout : 'fit',
	autoShow : true,
	width : 600,
	height : 400,
	buttonAlign : 'center',

	initComponent : function() {
		this.items = [{
	    	xtype : 'grid',
	    	id	  : 'teamusergrid_m',
	    	region : 'center',
	    	store : Ext.create('Admin.store.TeamUser'),
	    	tbar : [{
				text : 'Add',
				icon : contextPath+'/common/images/add.png',
				action : 'add'
			}],
	    	plugins: {
	    		pluginId : 'teamUserRowEditing',
		        ptype: 'rowediting',
		        listeners: {
		            cancelEdit: function(rowEditing, context) {
		                // Canceling editing of a locally added, unsaved record: remove it
		                if (context.record.phantom) {
		                    Ext.getCmp('teamusergrid_m').store.remove(context.record);
		                }
		            }
		            ,validateedit : function(e, c) {
		            	console.log(c);
		            }
		        }
		    },
	    	columns : [{
	    		header : '',
				xtype : 'actioncolumn',
				align : 'center',
				width : 50,
				items : [{
					icon : contextPath + '/common/images/delete.png',
					tooltip : 'Delete',
					handler : function(grid, rowIndex, colIndex) {
						this.up('teamuser').fireEvent('deletebuttonclick', grid, rowIndex, colIndex);
					}
				}]
	    	}, {
	    		header : 'ID',
	    		dataIndex : 'id',
	    		width : 120,
	            renderer: function(v, meta, rec) {
	                return rec.phantom ? '' : v;
	            }
	    	}, {
	    		header : 'Team',
				dataIndex : 'name',
				width : 120
	    	}, {
				header : 'User',
				dataIndex : 'userId',
				width : 120,
				editor : {
					xtype : 'combo',
					displayField : 'account',
					editable : false,
					valueField : 'id',
					store : Ext.create('Admin.store.User', {
						storeId : 'teamuser',
						proxy : {
							type : 'ajax',
							url : contextPath + '/user/getUser',
							reader : {
								type : 'json',
								rootProperty : 'recordlist'
							}
						},
						listeners : {
							'load' : function() {
								Ext.getCmp('teamusergrid_m').view.refresh();
							}
						}
					})
				},
				renderer : function(v, meta, rec, rowIndex, colIndex) {
					var editor = this.columns[colIndex].getEditor(),
						store = editor.store;
					if (store.getCount() > 0) {
						var record = store.findRecord(editor.valueField, v);
						return record.get(editor.displayField);
					} else {
						store.reload();
					}
				}
			}]
		}];

		this.callParent(arguments);
	}
});
