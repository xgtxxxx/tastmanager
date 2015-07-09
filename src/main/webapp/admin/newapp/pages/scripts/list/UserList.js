Ext.define('Admin.list.UserList', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.userlist',
	title : 'User List',
	header : false,
	hasOperate : true,
	hasPageBar : true,
	forceFit : true,
	url : contextPath + '/user',
	initComponent : function() {
		Ext.define('UserModel', {
			extend : 'Ext.data.Model',
			fields : [ 'id', 'account', 'password', 'locked', 'expired',
					'enabled', 'username' ]
		});
		this.store = Ext.create('Ext.data.Store', {
			model : 'UserModel',
			proxy : {
				type : 'ajax',
				url : this.url,
				reader : {
					type : 'json',
					rootProperty : 'recordlist',
					totalProperty : 'total'
				}
			}
		// ,
		// autoLoad: true
		});

		this.columns = [ {
			header : '',
			xtype : 'actioncolumn',
			menuDisabled : true,
			align : 'center',
			hidden : !this.hasOperate,
			width : 25,
			items : this.hasOperate ? [ {
				icon : contextPath + '/common/images/icons/page_white_delete.png',
				tooltip : 'Delete',
				scope : this,
				handler : this.doDelete
			} ] : []
		}, {
			header : '',
			xtype : 'actioncolumn',
			menuDisabled : true,
			align : 'center',
			hidden : !this.hasOperate,
			width : 25,
			items : this.hasOperate ? [ {
				icon : contextPath + '/common/images/icons/page_white_magnify.png',
				tooltip : 'Role',
				scope : this,
				handler : this.showRoleSetWin
			} ] : []
		}, {
			header : '',
			xtype : 'actioncolumn',
			menuDisabled : true,
			align : 'center',
			hidden : !this.hasOperate,
			width : 25,
			items : this.hasOperate ? [ {
				icon : contextPath + '/common/images/icons/page_white_gear.png',
				tooltip : 'Team',
				scope : this,
				handler : this.showTeamSetWin
			} ] : []
		}, {
			header : '',
			xtype : 'actioncolumn',
			menuDisabled : true,
			align : 'center',
			hidden : !this.hasOperate,
			width : 25,
			items : this.hasOperate ? [ {
				icon : contextPath + '/common/images/icons/page_white_key.png',
				tooltip : 'Reset Password',
				scope : this,
				handler : this.resetPassword
			} ] : []
		}, {
			header : 'ID',
			dataIndex : 'id',
			width : 30,
			renderer : function(v, meta, rec) {
				return rec.phantom ? '' : v;
			}
		}, {
			header : 'Account',
			dataIndex : 'account',
			width : 160
		}, {
			header : 'User Name',
			dataIndex : 'username',
			flex : 1
		// ,
		// width : 120
		}, {
			header : 'Unlocked',
			dataIndex : 'locked'
		// ,
		// width : 120
		}, {
			header : 'Unexpired',
			dataIndex : 'expired'
		// ,
		// width : 120
		}, {
			header : 'Enabled',
			dataIndex : 'enabled'
		// ,
		// width : 120
		} ]
		this.bbar = this.hasPageBar ? new Ext.PagingToolbar({
			pageSize : 20,
			store : this.store,
			displayInfo : true
		}) : "";
		this.callParent(arguments);
	},
	doDelete : function(grid, rowIndex, colIndex) {
		var id = grid.store.getAt(rowIndex).data.id;
		Ext.Msg.confirm("Notice", "are you sure?", function(v) {
			if (v == 'yes') {
				Ext.Ajax.request({
					url : contextPath + '/user/deleteUser',
					params : {
						id : id
					},
					success : function(response, option) {
						var obj = Ext.util.JSON.decode(response.responseText);
						grid.store.reload();
						Ext.Msg.alert("notice", obj.message);
					},
					failure : function() {
						Ext.Msg.alert("notice", "error!");
					}
				});
			}
		})
	},
	showRoleSetWin : function(grid, rowIndex, colIndex) {
		var params = {
			userId : grid.store.getAt(rowIndex).data.id
		}
		var win = Ext.create("Admin.win.RoleWin", {
			params : params
		});
		win.show();
		win.reloadOther(params);
		win.reloadMine(params);
	},
	showTeamSetWin : function(grid, rowIndex, colIndex) {
		var params = {
			userId : grid.store.getAt(rowIndex).data.id
		}
		var win = Ext.create("Admin.win.TeamWin", {
			params : params
		});
		win.show();
		win.reloadOther(params);
		win.reloadMine(params);
	},
	reload : function(params) {
		if (params) {
			Ext.apply(this.store.proxy.extraParams, params);
		}
		this.store.reload();
	},
	resetPassword : function(grid, rowIndex, colIndex) {
		var myMask = new Ext.LoadMask({
			msg : 'Please wait...',
			target : this
		});
		Ext.Msg.confirm("Confirm", "Reset password?", function(v) {
			if (v == 'yes') {
				myMask.show();
				Ext.Ajax.request({
					url : contextPath + '/user/resetPassword',
					params : {
						userId : grid.store.getAt(rowIndex).data.id
					},
					success : function(response, option) {
						var obj = Ext.util.JSON.decode(response.responseText);
						grid.store.reload();
						myMask.hide();
						Ext.Msg.alert("notice", obj.message);
					},
					failure : function() {
						myMask.hide();
						Ext.Msg.alert("notice", "error!");
					}
				});
			}
		});
	}
});