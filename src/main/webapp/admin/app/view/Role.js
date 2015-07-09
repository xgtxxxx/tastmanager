Ext.define('Admin.view.Role', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.role',
	
    title : 'Role',
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
	    	id	  : 'rolegrid_m',
	    	region : 'center',
	    	store : 'Role',
	    	plugins: {
	    		pluginId : 'roleRowEditing',
		        ptype: 'rowediting',
		        listeners: {
		            cancelEdit: function(rowEditing, context) {
		                // Canceling editing of a locally added, unsaved record: remove it
		                if (context.record.phantom) {
		                    Ext.getCmp('rolegrid_m').store.remove(context.record);
		                }
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
						this.up('role').fireEvent('deletebuttonclick', grid, rowIndex, colIndex);
					}
				}, {
					icon : contextPath + '/common/images/icons/user.png',
					tooltip : 'User',
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
				header : 'Name',
				dataIndex : 'name',
				width : 120,
	            editor: {
	                allowBlank: false,
	                xtype : 'textfield'
	            }
			}]
	    }];
	    
	    this.callParent(arguments);
    }
});