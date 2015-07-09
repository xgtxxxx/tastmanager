Ext.define('Task.view.task.TaskPanel', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.taskpanel',

    layout: 'fit',
    //the default query condition
    defaultQuery : {},
    //the type of the panel, the value is 'own' or 'team'
    taskType : '',
    padding  : '2',
    border : false,
    
    initComponent: function() {
    	this.items = [{
	        xtype: 'tasklist',
	        defaultQuery : this.defaultQuery,
	    	taskType : this.taskType
	    }];
    	
//    	this.items = [{
//	    	xtype : 'tasksearch',
//	    	region : 'north',
//	    	taskType : this.taskType
//	    } ,{
//	    	region : 'center',
//	        xtype: 'tasklist',
//	        defaultQuery : this.defaultQuery,
//	    	taskType : this.taskType
//	    }];
	    
	    this.callParent(arguments);
    }
});