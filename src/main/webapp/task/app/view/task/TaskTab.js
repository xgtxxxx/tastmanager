Ext.define('Task.view.task.TaskTab', {
	extend : 'Ext.tab.Panel',
	alias : 'widget.tasktab',
	border : false,
	initComponent : function() {
		this.items = [];
		// if the user is developer, show his/her tasks
		if (isDev) {
			this.items.push({
				xtype : 'taskpanel',
				title : 'My Task',
				border : false,
				// the type of the panel, the value is 'own' or 'team'
				taskType : 'own',
				// default query condition
				defaultQuery : {
					isOwn : "true"
				}
			});
		}
		// if the user is teamleader, show his/her teams' tasks
		if (!Ext.isEmpty(team)) {
			var teamObj = Ext.decode(team);
			if (Ext.isArray(teamObj) && teamObj.length > 0) {
				for (var i = 0, len = teamObj.length; i < len; i++) {
					this.items.push({
						xtype : 'taskpanel',
						title : teamObj[i].name,
						taskType : 'team',
						border : false,
						defaultQuery : {
							team : teamObj[i].id,
							submitDate : Ext.util.Format.date(new Date(),
									'Y-m-d'),
							isOwn : "false"
						}
					});
				}
			}
		}
		if (isAdmin) {
			Ext.Loader.setConfig({
				enabled : true,
				paths : {
					'Admin' : '../admin/newapp/pages/scripts'
				}
			});
			Ext.require([ 'Admin.Userpanel' ]);
			var panel = Ext.create("Admin.Userpanel", {
				title : "User",
				border : false,
				id : "userpanel"
			});
			var admin = Ext.create('Ext.panel.Panel', {
				layout : 'border',
				border : false,
				title : 'Admin',
				margin : '2',
				items : [ {
					xtype : 'menutree'
				}, {
					xtype : 'tabpanel',
					id : 'adminContainer',
					region : 'center',
					margin : '0 0 0 5',
					items : [ panel ]
				} ]
			});
			panel.reload();
			this.items.push(admin);
		}
		this.callParent(arguments);
	}
});