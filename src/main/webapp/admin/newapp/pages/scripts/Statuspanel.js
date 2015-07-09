Ext.define('Admin.Statuspanel', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.statuspanel',
    layout : 'border',
    requires : [ 'Admin.list.StatusList','Admin.ColorPicker' ],
    padding : '3',
    items : [{
    	region : 'center',
    	padding: '0 5 0 0',
    	xtype  : 'statuslist',
    	listeners : {
    		rowclick : function(t, record, tr, rowIndex, e, eOpts){
    			t.up("statuspanel").down('form').loadRecord(record);
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
        		name  : 'id'
        	},{
        		xtype : 'textfield',
        		name : 'description',
        		fieldLabel : 'Description'
        	},{
        		xtype : 'smmcolorpicker',
        		name : 'fontColor',
        		fieldLabel : 'Font Color'
        	},{
        		xtype : 'smmcolorpicker',
        		name : 'bgColor',
        		fieldLabel : 'Background Color'
        	}],
        	buttonAlign : 'center',
        	buttons : [{
        		text : 'Save',
        		handler : function(btn){
        			var grid = btn.up("statuspanel").items.items[0];
        			btn.up("form").getForm().submit({
    						url : contextPath+'/status/saveStatus',
    						waitTitle : 'wait...',
    						waitMsg : '...',
    						method : 'post',
    						params : {
    							test : 'test'
    						},
    						success : function(f, action) {
    							grid.store.reload();
    							btn.up("form").getForm().reset();
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
    	this.down("statuslist").reload();
    }
});