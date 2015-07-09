Ext.define('Admin.list.TeamList', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.teamlist',
	title : 'Team List',
	header : false,
	hasOperate : true,
	hasPageBar : true,
	forceFit : true,
	url : contextPath + '/team/getAll',
	initComponent : function() {
		Ext.define('TeamModel', {
			extend : 'Ext.data.Model',
			fields : [ 'id', 'name', 'teamleader', 'leader' ]
		});
		this.store = Ext.create('Ext.data.Store', {
			model : 'TeamModel',
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

		this.columns = [  {
			header : '',
			xtype : 'actioncolumn',
			menuDisabled : true,
			align : 'center',
			width : 9,
			handler : !this.hasOperate,
			items : this.hasOperate ? [ {
				icon : contextPath + '/common/images/icons/page_white_delete.png',
				tooltip : 'Delete',
				scope : this,
				handler : this.doDelete
			}] : []
		} ,{
			header : '',
			xtype : 'actioncolumn',
			menuDisabled : true,
			align : 'center',
			width : 9,
			handler : !this.hasOperate,
			items : this.hasOperate ? [{
				icon : contextPath + '/common/images/icons/page_white_magnify.png',
				tooltip : 'User',
				scope : this,
				handler : this.showUserSetWin
			} ] : []
		} ,{
			header : 'ID',
			dataIndex : 'id',
			renderer : function(v, meta, rec) {
				return rec.phantom ? '' : v;
			}
		}, {
			header : 'Name',
			dataIndex : 'name'
		}, {
			header : 'Team Leader',
			dataIndex : 'leader'
		}]
		this.bbar = this.hasPageBar ? new Ext.PagingToolbar({
			pageSize : 20,
			store : this.store,
			displayInfo : true
		}) : "";
		this.callParent(arguments);
	},
	doDelete : function(grid, rowIndex, colIndex) {
		var id = grid.store.getAt(rowIndex).data.id;
		Ext.Msg.confirm("notice", "are you sure?", function(v) {
			if (v == 'yes') {
				Ext.Ajax.request({
					url : contextPath + '/team/deleteTeam',
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
	showUserSetWin : function(grid, rowIndex, colIndex) {
		var params = {
			teamId : grid.store.getAt(rowIndex).data.id
		}
		var win = Ext.create("Admin.win.UserWin", {
			params : params,
			type : "Team"
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
	}
});