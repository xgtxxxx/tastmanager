Ext.define('Admin.Versionpanel', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.versionpanel',
    layout : 'border',
    requires : [ 'Admin.list.VersionList'],
    padding : '3',
    items : [{
    	region : 'center',
    	padding: '0 5 0 0',
    	xtype  : 'versionlist',
    	listeners : {
    		rowclick : function(t, record, tr, rowIndex, e, eOpts){
    			t.up("versionpanel").down('form').loadRecord(record);
    		}
    	}
    },{
    	layout : 'form',
    	region : 'east',
    	width  : 300,
    	items  : [{
        	xtype  : 'form',
        	title  : 'Add/Update Task Status',
        	labelAlign : 'right',
        	items  : [{
        		xtype : 'hidden',
        		name  : 'id',
        		value : 0
        	},{
        		xtype : 'textfield',
        		name : 'name',
        		fieldLabel : 'Name'
        	},{
        		xtype : 'combo',
        		name : 'active',
    			hiddenName : 'active',
    			fieldLabel : 'Status',
    			editable : false,
    			displayField : 'field1',
    			valueField : 'field2',
    			store : [ [ 3, 'For all members' ], [ 2, 'New add' ]],
    			value : 3,
    			queryMode : 'local'
        	}],
        	buttonAlign : 'center',
        	buttons : [{
        		text : 'Save',
        		handler : function(btn){
        			var grid = btn.up("versionpanel").items.items[0];
        			btn.up("form").getForm().submit({
    						url : contextPath+'/version/saveReleaseVersion',
    						waitTitle : 'wait...',
    						waitMsg : '...',
    						method : 'post',
    						success : function(f, action) {
    							grid.store.reload();
    							btn.up("form").getForm().reset();
    							Ext.Msg.alert("Notice", action.result.message);
    						},
    						failure : function(form, action) {
    							Ext.Msg.alert("Notice", action.result.message);
    						}
                     });
        		}
        	},{
        		text : 'Reset',
        		handler : function(btn){
        			btn.up("form").getForm().reset();
        		}
        	}]
    	}]
    	
    }],
    reload:function(){
    	this.down("versionlist").reload();
    }
});