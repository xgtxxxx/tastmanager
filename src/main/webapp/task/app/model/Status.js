Ext.define('Task.model.Status', {
    extend: 'Ext.data.Model',

    requires: [
        'Ext.data.reader.Json'
    ],

    fields: [
         'id', 
         'description',
         'fontColor',
         'bgColor'
    ]
});