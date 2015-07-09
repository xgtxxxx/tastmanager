Ext.define('Admin.Userpanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.userpanel',
	layout : 'border',
	requires : [ 'Admin.list.UserList' ],
	padding : '3',
	items : [ {
		region : 'center',
		padding : '0 5 0 0',
		xtype : 'userlist',
		listeners : {
			rowclick : function(t, record, tr, rowIndex, e, eOpts) {
				t.up("userpanel").down('form').loadRecord(record);
			}
		}
	}, {
		layout : 'column',
		region : 'east',
		border : false,
		width : 300,
		items : [ {
			columnWidth : 1,
			xtype : 'form',
			title : 'Add/Update User',
			labelAlign : 'right',
			items : [ {
				xtype : 'hidden',
				name : 'id'
			}, {
				margin : '20 0 0 5',
				xtype : 'textfield',
				name : 'account',
				fieldLabel : 'Account(Email)',
				vtype : 'email',
				vtypeText : 'Must to be a Email'
			}, {
				margin : '5 0 0 5',
				xtype : 'textfield',
				name : 'username',
				fieldLabel : 'User Name'
			}, {
				margin : '5 0 0 5',
				allowBlank : false,
				name : 'locked',
				hiddenName : 'locked',
				fieldLabel : 'Unlocked',
				xtype : 'combo',
				editable : false,
				displayField : 'field1',
				valueField : 'field2',
				store : [ [ false, 'no' ], [ true, 'yes' ] ],
				value : true,
				queryMode : 'local'
			}, {
				margin : '5 0 0 5',
				allowBlank : false,
				name : 'expired',
				hiddenName : 'expired',
				fieldLabel : 'Unexpired',
				xtype : 'combo',
				editable : false,
				displayField : 'field1',
				valueField : 'field2',
				store : [ [ false, 'no' ], [ true, 'yes' ] ],
				value : true,
				queryMode : 'local'
			}, {
				margin : '5 0 5 5',
				allowBlank : false,
				name : 'enabled',
				hiddenName : 'enabled',
				fieldLabel : 'Enabled',
				xtype : 'combo',
				editable : false,
				displayField : 'field1',
				valueField : 'field2',
				store : [ [ false, 'no' ], [ true, 'yes' ] ],
				value : true,
				queryMode : 'local'
			} ],
			buttonAlign : 'center',
			buttons : [ {
				text : 'Save',
				handler : function(btn) {
					var form = btn.up("form").getForm();
					var flag = form.isValid()
					if (!flag) {
						Ext.Msg.alert("notice", "Account mast to your email!");
						return;
					}
					var grid = btn.up("userpanel").items.items[0];
					form.submit({
						url : contextPath + '/user/saveUser',
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
			}, {
				text : 'Reset',
				handler : function(btn) {
					btn.up("form").getForm().reset();
				}
			} ]
		} ]

	} ],
	reload : function() {
		this.down("userlist").reload();
	}
});