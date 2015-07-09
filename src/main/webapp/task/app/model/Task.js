Ext.define('Task.model.Task', {
    extend: 'Ext.data.Model',

    requires: [
        'Ext.data.reader.Json'
    ],

    fields: [
         'id', 
         'taskId', 
         'description', 
         'releaseVersion',
         'progress',
         'status',
         'teamName',
         'statusDesc',
         'fontColor',
         'bgColor',
         'assignDate',
         'fixedDate',
         'owner',
         'ownerName',
         'commentsAndQuestions',
         'pendingIssues',
         'feedback',
         'submitTime',
         'lastUpdateTime',
         'team',
         'teamName',
         {
         	name : 'submitDate',
         	depends : 'submitTime',
         	convert : function(value, record) {
         		if (!value) {
	         		var date = Ext.Date.parse(record.get('submitTime'), "Y-m-d H:i:s", true);
	         		return Ext.Date.format(date, 'Y-m-d');
         		} else {
         			return value;
         		}
         	}
         }
    ]
});