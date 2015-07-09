Ext.define('Admin.Teampanel', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.teampanel',
    layout : 'border',
    requires : [ 'Admin.list.TeamList' ],
    padding : '3',
    items : [{
    	region : 'center',
    	padding: '0 5 0 0',
    	xtype  : 'teamlist',
    	listeners : {
    		rowclick : function(t, record, tr, rowIndex, e, eOpts){
    			t.up("teampanel").down('form').loadRecord(record);
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
        	title  : 'Add/Update Team',
        	labelAlign : 'right',
        	items  : [{
        		xtype : 'hidden',
        		name  : 'id'
        	},{
        		margin : '20 0 0 5',
        		xtype : 'textfield',
        		name : 'name',
        		fieldLabel : 'Name'
        	},{
        		margin : '5 0 10 5',
        		allowBlank : false,
        		name : 'teamleader',
        		hiddenName : 'teamleader',
    			fieldLabel : 'Team Leader',
    			xtype : 'combo',
    			editable : false,
    			displayField : 'account',
    			valueField : 'id',
    			store : Ext.create('Ext.data.Store', {
    			    proxy: {
    			        type: 'ajax',
    			        url: contextPath+'/user',
    			        reader: {
    			            type: 'json',
    			            rootProperty: 'recordlist',
    			            totalProperty: 'total'
    			        }
    			    },
    			    fields: ['id','account','password','locked','expired','enabled'],
    			    autoLoad: true
    			}),
    			queryMode : 'remote'
        	}],
        	buttonAlign : 'center',
        	buttons : [{
        		text : 'Save',
        		handler : function(btn){
        			var grid = btn.up("teampanel").items.items[0];
        			btn.up("form").getForm().submit({
    						url : contextPath+'/team/saveTeam',
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
    	this.down("teamlist").reload();
    }
});