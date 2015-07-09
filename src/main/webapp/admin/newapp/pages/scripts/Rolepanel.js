Ext.define('Admin.Rolepanel', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.rolepanel',
    layout : 'border',
    requires : [ 'Admin.list.RoleList' ],
    padding : '3',
    items : [{
    	region : 'center',
    	padding: '0 5 0 0',
    	xtype  : 'rolelist',
    	listeners : {
    		rowclick : function(t, record, tr, rowIndex, e, eOpts){
    			t.up("rolepanel").down('form').loadRecord(record);
    		}
    	}
    },{
    	layout : 'column',
    	region : 'east',
    	width  : 300,
    	items  : [{
    		columnWidth : 1,
    		border : false,
        	xtype  : 'form',
        	title  : 'Add/Update Role',
        	labelAlign : 'right',
        	items  : [{
        		xtype : 'hidden',
        		name  : 'id'
        	},{
        		margin : '20 0 10 5',
        		xtype : 'textfield',
        		name : 'name',
        		fieldLabel : 'name'
        	}],
        	buttonAlign : 'center',
        	buttons : [{
        		text : 'Save',
        		handler : function(btn){
        			var grid = btn.up("rolepanel").items.items[0];
        			btn.up("form").getForm().submit({
    						url : contextPath+'/role/saveRole',
    						waitTitle : 'wait...',
    						waitMsg : '...',
    						method : 'post',
    						params : {
    							test : 'test'
    						},
    						success : function(f, action) {
    							grid.store.reload();
    							Ext.Msg.alert("notice", action.result.message);
    						},
    						failure : function(form, action) {
    							Ext.Msg.alert("notice", action.result.message);
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
    	this.down("rolelist").reload();
    }
});