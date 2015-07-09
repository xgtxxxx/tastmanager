Ext.define('Monthly.Preview', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.preview',
    border : false,
    bodyPadding : '10px',
    params : {},
    initComponent: function() {
    	var p = this;
    	Ext.Ajax.request({
    		async : false,
			url: contextPath+'/email/previewMonthlyReport', 
			params : p.params,
			success: function(response, option) {
				var obj = Ext.util.JSON.decode(response.responseText);
				p.html=obj.message;
			},
			failure: function() {
				Ext.Msg.alert("notice","error!");	
			}
		});
    	this.callParent(arguments);
    }
});