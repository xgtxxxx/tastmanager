Ext.define('Task.store.Tasks', {
    extend: 'Ext.data.Store',
    model: 'Task.model.Task',
    autoLoad: true,
    pageSize : 25,
    sorters:["ownerName"],
    proxy: {
        type: 'ajax',
        url: contextPath+'/task/tasklist',
        reader: {
            type: 'json',
            rootProperty : 'recordlist',
            totalProperty : 'totalcount'
        }
    },
    groupField: 'submitDate',
    groupDir : 'DESC'
});