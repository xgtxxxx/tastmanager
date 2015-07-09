Ext.onReady(function() {
	var logoutBar = Ext.widget({
		xtype : 'toolbar',
		border : true,
		rtl : false,
		id : 'options-toolbar',
		floating : true,
//		fixed : true,
		preventFocusOnActivate : true,
		draggable : {
			constrain : true
		},
		defaults : {
			rtl : false
		},
		items : [ 
		    {
			tooltip : 'Options',
            xtype: 'splitbutton',
            menuAlign : "bl",
			icon : contextPath + '/common/images/icons/cog.png',
		    menu: new Ext.menu.Menu({
		        items: [
		            {text: 'Reset Password', handler: function(){ 
		            	Ext.apply(Ext.form.field.VTypes, {
		            		password: function(val, field) {
		                        if (field.initialPassField) {
		                            var pwd = field.up('form').down('#' + field.initialPassField);
		                            return (val == pwd.getValue());
		                        }
		                        return true;
		                    },
		                    passwordText: 'New password is different!',
		                    checkpsw: function(val, field) {
		                    	var flag = false;
		                    	Ext.Ajax.request({
		            				url: contextPath+'/user/checkPassword', 
		            				async : false,
		            				params : {
		            					password : val
		            				},
		            				success: function(response, option) {
		            					var obj = Ext.util.JSON.decode(response.responseText);
		            					if(obj.message=="success"){
		            						flag = true;
		            					}
		            				},
		            				failure: function() {
		            				}
		            			});
		                    	return flag;
		                    },
		                    checkpswText: 'Password is wrong!'
				    	});
		            	var win = Ext.create("Ext.window.Window",{
		            		height : 160,
		            		width  : 300,
		            		modal  : true,
		            		layout : 'fit',
		            		resizable : false,
		            		title  : "Change password",
		            		items  : [{
		            			xtype : 'form',
		            			border : false,
		            			bodyPadding : 5,
		            			defaults : {
		            				border : false
		            			},
		            			items : [{
		            				xtype : 'textfield',
		            				vtype :'checkpsw',
		            				inputType: 'password', 
		            				fieldLabel : 'Password',
		            				id   : 'psw',
		            				name : 'password',
		            				allowBlank : false
		            			},{
		            				xtype : 'textfield',
		            				inputType: 'password', 
		            				fieldLabel : 'New Password',
		            				id   : 'psw1',
		            				name : 'password1',
		            				allowBlank : false
		            			},{
		            				xtype : 'textfield',
		            				id   : 'psw2',
		            				inputType: 'password', 
		            				initialPassField: 'psw1',
		            				vtype : 'password',
		            				fieldLabel : 'Repeat',
		            				name : 'password2',
		            				allowBlank : false
		            			}]
		            		}],
			            	buttonAlign : 'center',
			            	buttons : [{
			            		text : 'Submit',
			            		handler : function(btn){
			            			var p1 = Ext.getCmp("psw1").getValue();
			            			var p2 = Ext.getCmp("psw2").getValue();
			            			if(p1!==p2){
			            				Ext.Msg.alert("Error","New password is difference");
			            				return;
			            			}
			            			var form = win.down("form").getForm();
			            			var f = form.isValid();
			            			if(!f){
			            				return;
			            			}
			            			form.submit({
				    						url : contextPath+'/user/changePassword',
				    						waitTitle : 'wait...',
				    						waitMsg : '...',
				    						method : 'post',
				    						success : function(f, action) {
				    							win.close();
				    							Ext.Msg.alert("Success", action.result.message);
				    						},
				    						failure : function(form, action) {
				    							Ext.Msg.alert("Error", action.result.message);
				    						}
				                     });
			            		}
			            	},{
			            		text : 'Cancel',
			            		handler : function(){
			            			win.close();
			            		}
			            	}]
		            	});
		            	 win.show();
		            }
		          },
		            {text: 'Edit Profile', handler: function(){ 
		            	
		            }}
		        ]
		    })
		},"-", {
			tooltip : 'Logout',
			icon : contextPath + '/common/images/icons/user_go.png',
			handler : function() {
				window.location = contextPath + '/logout';
			}
		} ],

		constraintInsets : '0 -' + (Ext.getScrollbarSize().width + 5) + ' 0 0'
	});
	logoutBar.show();
	logoutBar.anchorTo(document.body, Ext.optionsToolbarAlign || 'tr-tr', [
			-(Ext.getScrollbarSize().width + 50), 0 ], // adjust for scrollbar
														// offsets
	false, // anim
	true // monitor scroll
	);
});