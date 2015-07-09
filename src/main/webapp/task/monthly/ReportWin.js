Ext.define('Monthly.ReportWin', {
	extend : 'Ext.window.Window',
	alias : 'widget.reportwin',
    title : 'Monthly Task List',
    width : 1000,
    height: 640,
    layout : 'card',
    activeItem : 0,
    modal : true,
    buttonAlign : 'center',
    requires : [ "Monthly.ReportList","Monthly.GoalsList","Monthly.Preview" ],
    initComponent: function() {
        this.items = [{
        	border : false,
        	layout : 'border',
        	items : [{
            	xtype : 'reportlist',
            	padding : '5 5 5 5',
            	border  : true,
            	height  : 350,
            	region:'north'
            },{
            	xtype : 'goalslist',
            	padding : '5 5 5 5',
            	border  : true,
            	height: 120,
            	region:'center'
            },{
            	xtype : 'form',
            	header : false,
            	padding : '5 5 5 5',
            	height: 100,
            	region:'south',
            	bodyPadding : 2,
            	layout : 'fit',
            	tbar : ["<strong>Notes</strong>"],
            	items : [{
            		xtype : 'textarea'
            	}]
            }]
        }],
        this.buttons=[{
        	text : 'Export Excel',
        	icon : contextPath+'/common/images/icons/page_white_excel.png',
        	scope : this,
        	handler : this.exportExcel
        },{
        	text : 'Preview',
        	scope : this,
        	icon : contextPath+'/common/images/icons/email_open_image.png',
        	handler : this.preview
        },{
        	text : 'Go Back',
        	hidden : true,
        	scope : this,
        	icon : contextPath+'/common/images/icons/cup_go.png',
        	handler : this.goback
        },{
        	text : 'Send Email',
        	scope : this,
        	icon : contextPath+'/common/images/icons/email_go.png',
        	handler : this.sendEmail
        },{
        	text : 'Close',
        	scope : this,
        	icon : contextPath+'/common/images/icons/link_break.png',
        	handler : function(){
        		this.close();
        	}
        }],
        this.callParent(arguments);
    },
    sendEmail : function(btn){
    	var ids = this.down("reportlist").getSelectedIds();
    	var goals = this.down("goalslist").getGoals();
    	var notes = this.down("textarea").getValue();
    	this.doSend(ids,goals,notes);
    },
    doSend : function(ids,goals,notes){
    	var win = this;
    	var myMask = new Ext.LoadMask({
    	    msg    : 'Please wait...',
    	    target : this
    	});
    	Ext.Msg.confirm("notice","are you sure?",function(v){
    		if(v=='yes'){
    			myMask.show();
    			Ext.Ajax.request({
    				url: contextPath+'/email/sendMonthlyReport', 
    				params : {
    					ids : ids,
    					goals : goals,
    					notes : notes
    				},
    				success: function(response, option) {
    					var obj = Ext.util.JSON.decode(response.responseText);
    					myMask.hide();
    					Ext.Msg.alert("notice",obj.message);
    					win.close();
    				},
    				failure: function() {
    					Ext.Msg.alert("notice","error!");	
    				}
    			});
    		}
    	});
    },
    exportExcel : function(){
    	var url=contextPath+"/task/exportMonthlyTask?type=excel";
		window.location.href=url;
    },
    preview : function(btn){
    	var ids = this.down("reportlist").getSelectedIds();
    	var goals = this.down("goalslist").getGoals();
    	var notes = this.down("textarea").getValue();
    	var win = this;
    	var panel = Ext.create("Monthly.Preview",{
    		params : {
    			ids : ids,
				goals : goals,
				notes : notes
    		}
    	});
    	this.add(panel);
    	var l = this.getLayout();
    	l.next();
    	this.changeTitle("Preview Monthly Report");
    	this.showBack(btn);
    },
    changeTitle : function(title){
    	this.setTitle(title);
    },
    showBack : function(btn){
    	var tb = btn.ownerCt;
    	tb.items.items[2].show();
    	tb.items.items[0].hide();
    	tb.items.items[1].hide();
    },
    showExport : function(btn){
    	var tb = btn.ownerCt;
    	tb.items.items[2].hide();
    	tb.items.items[0].show();
    	tb.items.items[1].show();
    },
    goback : function(btn){
    	var l = this.getLayout();
    	l.prev();
    	var p = this.down("preview");
    	this.remove(p);
    	this.changeTitle("Monthly Task List");
    	this.showExport(btn);
    }
});