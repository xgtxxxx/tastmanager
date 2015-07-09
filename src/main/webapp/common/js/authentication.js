Ext.onReady(function() {
	Ext.Ajax.addListener('requestexception', function(conn, response, options,
			eOpts) {
		if (response.status == 401) {
			Task.common.authentication.loginWin.show();
		}
	}, this);
});

Ext.namespace('Task.common.authentication');
Task.common.authentication.loginWin = Ext.create('Ext.window.Window', {
	title : 'Login Panel',
	height : 140,
	width : 400,
	bodyStyle : 'border:none',
	closeAction : 'hide',
	modal : true,
	layout : 'fit',
	items : [ {
		xtype : 'form',
		bodyPadding : 5,
		border : 1,
		url : contextPath + '/clientLoginProcess',
		layout : 'anchor',
		handleSuccss : function(form, action) {
			Task.common.authentication.loginWin.close();
		},
		handleFailure : function(form, action) {
			Ext.Msg.alert('Failed', action.result.msg);
		},
		defaults : {
			anchor : '96%',
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == e.ENTER) {
						var form = field.up('form').getForm();
						var formPanel = form.owner;
						form.submit({
							success : formPanel.handleSuccss,
							failure : formPanel.handleFailure
						});
					}
				}
			}
		},
		defaultType : 'textfield',
		items : [ {
			fieldLabel : 'Username',
			name : 'j_username',
			allowBlank : false
		}, {
			fieldLabel : 'Password',
			inputType : 'password',
			name : 'j_password',
			allowBlank : false
		} ],

		// Reset and Submit buttons
		buttons : [ {
			text : 'Reset',
			handler : function() {
				this.up('form').getForm().reset();
			}
		}, {
			text : 'Login',
			formBind : true, // only enabled once the form is valid
			disabled : true,
			handler : function() {
				var form = this.up('form').getForm();
				if (form.isValid()) {
					var formPanel = form.owner;
					form.submit({
						success : formPanel.handleSuccss,
						failure : formPanel.handleFailure
					});
				}
			}
		} ]
	} ]
});