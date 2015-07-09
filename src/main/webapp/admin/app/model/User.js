Ext.define('Admin.model.User', {
    extend: 'Ext.data.Model',

    requires: [
        'Ext.data.reader.Json'
    ],

    fields: [
         'id', 
         'account',
         'password',
         'locked',
         'expired',
         'enabled'
    ],
    validations: [{
        type: 'length',
        field: 'account',
        min: 1
    }]
});