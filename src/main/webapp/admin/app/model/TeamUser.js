Ext.define('Admin.model.TeamUser', {
    extend: 'Ext.data.Model',

    requires: [
        'Ext.data.reader.Json'
    ],

    fields: [
    	'id', 
        'teamId',
        'userId',
        'name'
    ],
    validations: [{
        type: 'length',
        field: 'userId',
        min: 1
    }]
});