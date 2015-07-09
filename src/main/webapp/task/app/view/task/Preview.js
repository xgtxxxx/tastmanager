Ext.define('Task.view.task.Preview', {
	extend : 'Ext.window.Window',
    title : 'Preview daily report',
    width : 1200,
    height: 640,
    layout : 'fit',
    modal : true,
    buttonAlign : 'center',
    url   : "",
    params : {},
    initComponent: function() {
    	var html = "";
    	var p = this.params;
    	p.preview = true;
    	Ext.Ajax.request({
    		async : false,
			url: this.url, 
			params : p,
			success: function(response, option) {
				var obj = Ext.util.JSON.decode(response.responseText);
				html=obj.message;
			},
			failure: function() {
			}
		});
        var panel = Ext.create("Ext.panel.Panel",{
        	border : false,
            bodyPadding : '10px',
            autoScroll : true,
            html : html
        });
        
        this.items = [panel];
    	
        this.buttons=[{
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
    sendEmail : function(){
    	var win = this;
    	win.params.preview = false;
    	var myMask = new Ext.LoadMask({
    	    msg    : 'Please wait...',
    	    target : this
    	});
    	var notice = "";
    	Ext.Ajax.request({
			url: contextPath+"/email/checkSysSend", 
			async : false,
			success: function(response, option) {
				var obj = Ext.util.JSON.decode(response.responseText);
				notice = obj.message;
			},
			failure: function() {
			}
		});
    	Ext.Msg.confirm("Notice",notice,function(v){
    		if(v=='yes'){
    			myMask.show();
    			Ext.Ajax.request({
    				url: win.url, 
    				params : win.params,
    				success: function(response, option) {
    					var obj = Ext.util.JSON.decode(response.responseText);
    					myMask.hide();
    					Ext.Msg.alert("notice",obj.message);
    					win.close();
    				},
    				failure: function() {
    					myMask.hide();
    					Ext.Msg.alert("Notice","Error!");	
    				}
    			});
    		}
    	});
    }
});