Ext.define('Admin.win.TeamWin', {
	extend : 'Ext.window.Window',
	alias : 'widget.teamwin',
    title : 'team set',
    width : 800,
    height: 350,
    layout : 'border',
    modal : true,
    params     : {},
    requires : [ 'Admin.list.TeamList' ],
    items : [{
    	width : 371,
    	region:'west',
    	title : 'all teams',
    	hasPageBar : false,
    	hasOperate : false,
    	xtype : 'teamlist',
    	url   : contextPath+'/team/listOtherTeams'
    },{
    	region:'center',
    	layout : 'form',
    	frame : true,
    	border : false,
    	style    : 'padding:50px 0px 0px 0px;',
    	items : [{
    		icon  :  contextPath + '/common/images/icons/arrow_right.png',
    		xtype : 'button',
    		handler : function(){
    			this.up("teamwin").doAdd();
    		}
    	},{
    		icon  :  contextPath + '/common/images/icons/arrow_left.png',
    		xtype : 'button',
    		handler : function(){
    			this.up("teamwin").doDel();
    		}
    	}]
    },{
    	width : 371,
    	region:'east',
    	hasPageBar : false,
    	hasOperate : false,
    	title : 'my teams',
    	xtype : 'teamlist',
    	url   : contextPath+'/team/listMyTeams'
    }],
    doAdd : function(){
    	var userId = this.params.userId;
    	var other = this.items.items[0];
    	var mine = this.items.items[2];
    	var res = other.getSelection();
    	if(res.length!=1){
    		Ext.Msg.alert("notice","please select one to add!");
    		return;
    	}
    	this.doUpdate(userId,res[0].data.id,"add");
    },
    doDel : function(){
    	var userId = this.params.userId;
    	var other = this.items.items[0];
    	var mine = this.items.items[2];
    	var res = mine.getSelection();
    	if(res.length!=1){
    		Ext.Msg.alert("notice","please select one to delete!");
    		return;
    	}
    	this.doUpdate(userId,res[0].data.id,"del");
    },
    doUpdate : function(userId,teamId,flag){
    	var win = this;
    	Ext.Ajax.request({
			url: contextPath+'/team/doSetTeam', 
			params : {
				userId : userId,
				teamId : teamId,
				flag   : flag
			},
			success: function(response, option) {
				var obj = Ext.util.JSON.decode(response.responseText);
				win.reloadOther();
				win.reloadMine();
			},
			failure: function() {
				Ext.Msg.alert("notice","error!");	
			}
		});
    },
    reloadOther : function(params){
    	this.items.items[0].reload(params);
    },
    reloadMine : function(params){
    	this.items.items[2].reload(params);
    }
});