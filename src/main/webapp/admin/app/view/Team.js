Ext.define('Admin.view.Team', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.team',
	
    title : 'Team',
    layout: 'border',
    frame: true,
	
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
	    	id	  : 'teamgrid_m',
	    	region : 'center',
	    	store : 'Team',
	    	plugins: {
	    		pluginId : 'teamRowEditing',
		        ptype: 'rowediting',
		        listeners: {
		            cancelEdit: function(rowEditing, context) {
		                // Canceling editing of a locally added, unsaved record: remove it
		                if (context.record.phantom) {
		                    Ext.getCmp('teamgrid_m').store.remove(context.record);
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
						this.up('team').fireEvent('deletebuttonclick', grid, rowIndex, colIndex);
					}
				}, {
					icon : contextPath + '/common/images/icons/user.png',
					tooltip : 'User',
					handler : function(grid, rowIndex, colIndex) {
						var record = grid.store.getAt(rowIndex);
						Ext.create('Admin.view.TeamUser', {
			    			title : 'Team-' + record.get('name'),
			    			renderTo : grid.ownerCt.getEl(),
			    			teamid : record.get('id'),
			    			name : record.get('name')
			    		}).show();
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
			} , {
				header : 'Team Leader',
				dataIndex : 'teamleader',
				width : 120,
				editor : {
					xtype : 'combo',
					displayField : 'account',
					editable : false,
					valueField : 'id',
					store : Ext.create('Admin.store.User', {
						storeId : 'teamleader',
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
								Ext.getCmp('teamgrid_m').view.refresh();
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