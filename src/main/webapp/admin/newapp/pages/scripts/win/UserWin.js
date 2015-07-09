Ext.define('Admin.win.UserWin', {
	extend : 'Ext.window.Window',
	alias : 'widget.userwin',
    title : 'user set',
    width : 800,
    height: 350,
    layout : 'border',
    modal : true,
    requires : [ 'Admin.list.UserList' ],
    type  : '',
    initComponent: function() {
    	this.items = [{
        	width : 371,
        	region:'west',
        	title : 'all users',
        	hasPageBar : false,
        	hasOperate : false,
        	xtype : 'userlist',
        	url   : contextPath+'/user/listOtherUserBy'+this.type
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
        			this.up("userwin").doAdd();
        		}
        	},{
        		icon  :  contextPath + '/common/images/icons/arrow_left.png',
        		xtype : 'button',
        		handler : function(){
        			this.up("userwin").doDel();
        		}
        	}]
        },{
        	width : 371,
        	region:'east',
        	hasPageBar : false,
        	hasOperate : false,
        	title : 'my users',
        	xtype : 'userlist',
        	url   : contextPath+'/user/listMyUserBy'+this.type
        }],
    	this.callParent(arguments);
    },
//    items : [{
//    	width : 371,
//    	region:'west',
//    	title : 'all users',
//    	hasPageBar : false,
//    	hasOperate : false,
//    	xtype : 'userlist',
//    	url   : contextPath+'/user/listOtherUserBy'+this.ownerCt.type
//    },{
//    	region:'center',
//    	layout : 'form',
//    	frame : true,
//    	border : false,
//    	style    : 'padding:50px 0px 0px 0px;',
//    	items : [{
//    		icon  :  contextPath + '/common/images/add.png',
//    		xtype : 'button',
//    		handler : function(){
//    			this.up("userwin").doAdd();
//    		}
//    	},{
//    		icon  :  contextPath + '/common/images/delete.png',
//    		xtype : 'button',
//    		handler : function(){
//    			this.up("userwin").doDel();
//    		}
//    	}]
//    },{
//    	width : 371,
//    	region:'east',
//    	hasPageBar : false,
//    	hasOperate : false,
//    	title : 'my users',
//    	xtype : 'userlist',
//    	url   : contextPath+'/user/listMyUserBy'+this.type
//    }],
    doAdd : function(){
    	var roleId = this.params.roleId;
    	var teamId = this.params.teamId;
    	var other = this.items.items[0];
//    	var mine = this.items.items[2];
    	var res = other.getSelection();
    	if(res.length!=1){
    		Ext.Msg.alert("notice","please select one to add!");
    		return;
    	}
    	var type = "";
    	if(roleId&&teamId){
    		Ext.Msg.alert("notice","error:has both teamId and roleId");
    		return;
    	}
    	if((!roleId)&&(!teamId)){
    		Ext.Msg.alert("notice","error:no teamId or roleId");
    		return;
    	}
    	if(roleId){
    		type = "role";
    	}else{
    		type = "team";
    	}
    	this.doUpdate(roleId,teamId,res[0].data.id,"add",type);
    },
    doDel : function(){
    	var roleId = this.params.roleId;
    	var teamId = this.params.teamId;
//    	var other = this.items.items[0];
    	var mine = this.items.items[2];
    	var res = mine.getSelection();
    	if(res.length!=1){
    		Ext.Msg.alert("notice","please select one to delete!");
    		return;
    	}
    	var type = "";
    	if(roleId&&teamId){
    		Ext.Msg.alert("notice","error:has both teamId and roleId");
    		return;
    	}
    	if((!roleId)&&(!teamId)){
    		Ext.Msg.alert("notice","error:no teamId or roleId");
    		return;
    	}
    	if(roleId){
    		type = "role";
    	}else{
    		type = "team";
    	}
    	this.doUpdate(roleId,teamId,res[0].data.id,"del",type);
    },
    doUpdate : function(roleId,teamId,userId,flag,type){
    	var win = this;
    	Ext.Ajax.request({
			url: contextPath+'/user/doSetUser', 
			params : {
				userId : userId,
				teamId : teamId,
				roleId : roleId,
				flag   : flag,
				type   : type
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