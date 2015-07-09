Ext.define('Admin.win.RoleWin', {
	extend : 'Ext.window.Window',
	alias : 'widget.rolewin',
    title : 'role set',
    width : 800,
    height: 350,
    layout : 'border',
    modal : true,
    params : {},
    requires : [ 'Admin.list.RoleList' ],
    items : [{
//    	columnWidth : .45,
//    	height : 268,
    	width : 371,
    	region:'west',
    	title : 'all roles',
    	hasPageBar : false,
    	hasOperate : false,
    	xtype : 'rolelist',
    	url   : contextPath+'/role/listOtherRoles'
    },{
//    	columnWidth : .1,
    	region:'center',
    	layout : 'form',
    	frame : true,
    	border : false,
    	style    : 'padding:50px 0px 0px 0px;',
    	items : [{
    		icon  :  contextPath + '/common/images/icons/arrow_right.png',
    		xtype : 'button',
    		handler : function(){
    			this.up("rolewin").doAdd();
    		}
    	},{
    		icon  :  contextPath + '/common/images/icons/arrow_left.png',
    		xtype : 'button',
    		handler : function(){
    			this.up("rolewin").doDel();
    		}
    	}]
    },{
//    	columnWidth : .45,
//    	height : 268,
    	width : 371,
    	region:'east',
    	hasPageBar : false,
    	hasOperate : false,
    	title : 'my roles',
    	xtype : 'rolelist',
    	url   : contextPath+'/role/listMyRoles'
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
    	Ext.Ajax.request({
			url: contextPath+'/role/doSetRole', 
			params : {
				userId : userId,
				roleId : res[0].data.id,
				flag   : 'add'
			},
			success: function(response, option) {
				var obj = Ext.util.JSON.decode(response.responseText);
				other.reload();
				mine.reload();
//				Ext.Msg.alert("notice",obj.message);	
			},
			failure: function() {
				Ext.Msg.alert("notice","error!");	
			}
		});
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
    	Ext.Ajax.request({
			url: contextPath+'/role/doSetRole', 
			params : {
				userId : userId,
				roleId : res[0].data.id,
				flag   : 'del'
			},
			success: function(response, option) {
				var obj = Ext.util.JSON.decode(response.responseText);
				other.reload();
				mine.reload();
//				Ext.Msg.alert("notice",obj.message);	
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