Ext.define('Task.store.Status', {
    extend: 'Ext.data.Store',
    model: 'Task.model.Status',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url:  contextPath+'/status/listStatus',
        reader: {
            type: 'json',
            rootProperty : 'recordlist',
            totalProperty : 'totalcount'
        }
    }
});