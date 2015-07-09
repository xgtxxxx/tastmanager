Ext.define('Admin.Menutree', {
	extend : 'Ext.tree.Panel',
	alias : 'widget.menutree',
	region : 'west',
	title : 'Admin Menu',
	collapsible : true,
	collapsed : true,
	split : true,
	width : 200,
	rootVisible : false,
	initComponent : function() {
		this.store = Ext.create('Ext.data.TreeStore', {
			root : {
				expanded : true,
				children : [ {
					text : "User",
					leaf : true,
					xtype : 'userpanel',
					clazz : 'Admin.Userpanel',
					icon : contextPath + '/common/images/icons/wrench.png'
				}, {
					text : "Role",
					leaf : true,
					xtype : 'rolepanel',
					clazz : 'Admin.Rolepanel',
					icon : contextPath + '/common/images/icons/wrench.png'
				}, {
					text : "Team",
					leaf : true,
					xtype : 'teampanel',
					clazz : 'Admin.Teampanel',
					icon : contextPath + '/common/images/icons/wrench.png'
				}, {
					text : "Status",
					leaf : true,
					xtype : 'statuspanel',
					clazz : 'Admin.Statuspanel',
					icon : contextPath + '/common/images/icons/wrench.png'
				}, {
					text : "ReleaseVersion",
					leaf : true,
					xtype : 'versionpanel',
					clazz : 'Admin.Versionpanel',
					icon : contextPath + '/common/images/icons/wrench.png'
				} , {
					text : "Email Config",
					leaf : true,
					xtype : 'emailconfig',
					clazz : 'Admin.Emailpanel',
					icon : contextPath + '/common/images/icons/wrench.png'
				} ]
			}
		});
		this.callParent(arguments);
	},
	listeners : {
		cellclick : function(t, td, cellIndex, record, tr, rowIndex, e, eOpts) {
			var tab = Ext.getCmp('adminContainer');
			var panel = tab.getChildByElement(record.data.xtype);
			if (!panel) {
				Ext.Loader.setConfig({
					enabled : true,
					paths : {
						'Admin' : '../admin/newapp/pages/scripts'
					}
				});
				Ext.require([ record.data.clazz]);
				var panel = Ext.create(record.data.clazz, {
					title : record.data.text,
					border : false,
					id : record.data.xtype,
					closable : true
				});
				
				tab.add(panel);
			}
			tab.setActiveTab(panel);
			panel.reload();
		}
	}
});