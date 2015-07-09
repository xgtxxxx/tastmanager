Ext.define('Admin.Emailpanel', {
    extend: 'Ext.form.Panel',
    alias : 'widget.emailspanel',
    layout : 'column',
    defaults : {
		border : false
	},
	initComponent : function() {
		 this.tbar = ["Email Config","->",{
		    	text : 'Save',
		    	icon : contextPath+'/common/images/icons/table_save.png',
		    	scope : this,
		    	handler : this.doSave
		    },"-",{
		    	text : 'Refresh',
		    	icon : contextPath+'/common/images/icons/table_refresh.png',
		    	scope : this,
		    	handler : this.reload
		    }];
	    this.callParent(arguments);
	},
    items : [{
    	columnWidth : .5,
    	xtype : 'form',
    	padding : '20 0 0 50',
    	defaults : {
    		anchor : "90%"
    	},
    	items : [{
    		xtype : 'hidden',
    		name  : 'id'
    	},{
    		xtype : 'textfield',
    		fieldLabel : 'Host',
    		name  : 'host',
    		allowBlank : false
    	},{
    		xtype : 'textfield',
    		fieldLabel : 'User Name',
    		name  : 'userName',
    		allowBlank : false,
			vtype : 'email',
			vtypeText : 'Must to be a Email'
    	},{
    		xtype : 'textfield',
    		fieldLabel : 'Daily Time',
    		name  : 'dailyTime'
    	},{
    		fieldLabel : 'Start Tls',
    		name  : 'startTls',
    		allowBlank : false,
    		hiddenName : 'startTls',
			xtype : 'combo',
			editable : false,
			displayField : 'field1',
			valueField : 'field2',
			store : [ [ false, 'false' ], [ true, 'true' ] ],
			queryMode : 'local'
    	},{
    		xtype : 'textfield',
    		fieldLabel : 'Daily To',
    		name  : 'dailyTo',
    		allowBlank : false,
			vtype : 'email',
			vtypeText : 'Must to be a Email'
    	},{
    		xtype : 'textfield',
    		fieldLabel : 'Daily CC',
    		name  : 'dailyCc',
			vtype : 'email',
			vtypeText : 'Must to be a Email'
    	},{
    		xtype : 'textfield',
    		fieldLabel : 'Monthly To',
    		name  : 'monthlyTo',
    		allowBlank : false,
			vtype : 'email',
			vtypeText : 'Must to be a Email'
    	},{
    		xtype : 'textfield',
    		fieldLabel : 'Monthly CC',
    		name  : 'monthlyCc',
			vtype : 'email',
			vtypeText : 'Must to be a Email'
    	}]
    },{
    	columnWidth : .5,
    	xtype : 'form',
    	padding : '20 50 0 0',
    	defaults : {
    		anchor : "90%"
    	},
    	items : [{
    		xtype : 'hidden',
    		name  : 'debug'
    	},{
    		xtype : 'numberfield',
    		fieldLabel : 'Port',
    		name  : 'port',
    		allowBlank : false
    	},{
    		xtype : 'textfield',
    		fieldLabel : 'Password',
    		name  : 'password',
    		inputType : 'password',
    		allowBlank : false
    	},{
    		xtype : 'textfield',
    		fieldLabel : 'Notice Time',
    		name  : 'noticeTime'
    	},{
    		fieldLabel : 'Auth',
    		name  : 'auth',
    		allowBlank : false,
    		hiddenName : 'auth',
			xtype : 'combo',
			editable : false,
			displayField : 'field1',
			valueField : 'field2',
			store : [ [ false, 'false' ], [ true, 'true' ] ],
			queryMode : 'local'
    	},{
    		xtype : 'textfield',
    		fieldLabel : 'Daily Bcc',
    		name  : 'dailyBcc',
			vtype : 'email',
			vtypeText : 'Must to be a Email'
    	},{
    		xtype : 'textfield',
    		fieldLabel : 'Daily Subject',
    		name  : 'dailySubject'
    	},{
    		xtype : 'textfield',
    		fieldLabel : 'Monthly Bcc',
    		name  : 'monthlyBcc',
			vtype : 'email',
			vtypeText : 'Must to be a Email'
    	},{
    		xtype : 'textfield',
    		fieldLabel : 'Monthly Subject',
    		name  : 'monthlySubject'
    	}]
    }],
    reload:function(){
    	this.getForm().load({
    	    url: contextPath + '/emailconfig/getEmailConfig',
    	    failure: function(form, action) {
    	        Ext.Msg.alert("Load failed", action.result.errorMessage);
    	    }
    	});
    },
    doSave : function(){
    	var form = this.getForm();
    	if (form.isValid()) {
    		Ext.Msg.confirm("Notice","Save Now?",function(v){
    			if(v=="yes"){
    				form.submit({
    					url : contextPath+'/emailconfig/saveEmailConfig',
						waitTitle : 'wait...',
						waitMsg : '...',
						method : 'post',
			            success: function(form, action) {
			                Ext.Msg.alert('Success', action.result ? action.result.message : 'Success!');
			            },
			            failure: function(form, action) {
			                Ext.Msg.alert('Failed', action.result ? action.result.message : 'No response');
			            }
			         });
    			}
    		})
    		
    	}
    }
});