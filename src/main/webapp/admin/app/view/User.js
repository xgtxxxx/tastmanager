Ext.define('Admin.view.User', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.user',
	
    title : 'User',
    layout: 'border',
	
    initComponent: function() {
    	this.items = [{
	    	xtype : 'form',
	    	region : 'north',
	    	items : [{
	    		labelWidth : 40,
	    		xtype : 'textfield',
	    		margin : '5px',
	    		fieldLabel : 'Name',
				width : 200,
	    		name : 'name'
	    	}],
	    	buttonAlign : 'left',
	    	buttons : [{
				text : 'Add',
				icon : contextPath+'/common/images/add.png',
				action : 'add'
			}, '-', {
				text : 'Export',
				icon : contextPath+'/common/images/icons/page_white_excel.png',
				action : 'export'
			}, {
				text : 'Filter',
				icon : contextPath+'/common/images/icons/find.png',
				action : 'search'
			}]
	    } , {
	    	xtype : 'grid',
	    	region : 'center',
	    	store : 'User',
	    	id	  : 'usergrid_m',
	    	plugins: {
	    		pluginId : 'userRowEditing',
		        ptype: 'rowediting',
		        listeners: {
		            cancelEdit: function(rowEditing, context) {
		                // Canceling editing of a locally added, unsaved record: remove it
		                if (context.record.phantom) {
		                    Ext.getCmp('usergrid_m').store.remove(context.record);
		                }
		            }
		        }
		    },
	    	columns : [{
	    		header : '',
				xtype : 'actioncolumn',
				align : 'center',
				width : 75,
				items : [{
					icon : contextPath + '/common/images/delete.png',
					tooltip : 'Delete',
					handler : function(grid, rowIndex, colIndex) {
						this.up('user').fireEvent('deletebuttonclick', grid, rowIndex, colIndex);
					}
				}, {
					icon : contextPath + '/common/images/icons/key.png',
					tooltip : 'Role',
					handler : function(grid, rowIndex, colIndex) {
						
					}
				}, {
					icon : contextPath + '/common/images/icons/group.png',
					tooltip : 'Team',
					handler : function(grid, rowIndex, colIndex) {
						
					}
				}]
	    	}, {
				header : 'ID',
				dataIndex : 'id',
				width : 120,
	            renderer: function(v, meta, rec) {
	                return rec.phantom ? '' : v;
	            }
			} , {
				header : 'Account',
				dataIndex : 'account',
				width : 120,
	            editor: {
	                allowBlank: false,
	                xtype : 'textfield'
	            }
			} , {
				header : 'Password',
				dataIndex : 'password',
				width : 120,
	            editor: {
	                allowBlank: false,
	                inputType : 'password',
	                xtype : 'textfield'
	            },
	            renderer: function(v, meta, rec) {
	                return '******';
	            }
			} , {
				header : 'Locked',
				dataIndex : 'locked',
				width : 120,
	            editor: {
	                xtype: 'checkbox',
	                cls: 'x-grid-checkheader-editor'
	            }
			} , {
				header : 'Expired',
				dataIndex : 'expired',
				width : 120,
	            editor: {
	                xtype: 'checkbox',
	                cls: 'x-grid-checkheader-editor'
	            }
			} , {
				header : 'Enabled',
				dataIndex : 'enabled',
				width : 120,
	            editor: {
	                xtype: 'checkbox',
	                cls: 'x-grid-checkheader-editor'
	            }
			}]
	    }];
	    
	    this.callParent(arguments);
    }
});