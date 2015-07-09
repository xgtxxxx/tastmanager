Ext.define('Task.view.task.Search', {
	extend : 'Ext.form.Panel',
	alias : 'widget.tasksearch',

	requires : [ 'Ext.layout.container.Column', 'Ext.layout.container.Form',
			'Ext.form.field.ComboBox' ],
	border : false,
	// title : 'Search Task',
	layout : 'fit',
	autoShow : true,
	// the type of the panel, the value is 'own' or 'team'
	taskType : '',

	initComponent : function() {
		var taskID = {
			xtype : 'textfield',
			name : 'taskId',
			anchor : '99%',
			fieldLabel : 'Task ID'
		};
		var team = {
			xtype : 'combo',
			name : 'team',
			fieldLabel : 'Team',
			displayField : 'name',
			valueField : 'id',
			hiddenName : 'team',
//			multiSelect : true,
			editable : false,
			anchor : '99%',
			store : 'Team',
			queryMode : 'remote'
		};
		var status = {
			xtype : 'combo',
			name : 'status',
			fieldLabel : 'Status',
			displayField : 'description',
			valueField : 'id',
			editable : false,
//			multiSelect : true,
			anchor : '99%',
			hiddenName : 'status',
			store : 'Status',
			queryMode : 'remote'
		};
		var owner = {
			xtype : 'textfield',
			name : 'owner',
			anchor : '99%',
			fieldLabel : 'Owner'
		};

		if (this.taskType === 'own') {
			this.items = [ {
				xtype : 'form',
				padding : '5 5 0 5',
				border : false,
				//style : 'background-color: #fff;',
				layout : 'column',
				defaults : {
					border : false,
					layout : 'form',
					columnWidth : .17
				},
				items : [ {
					items : [ taskID ]
				}, {
					items : [ team ]
				}, {
					items : [ status ]
				} ]
			} ];

			this.buttons = [ {
				text : 'Add',
				icon : contextPath+'/common/images/add.png',
				action : 'add'
			}, '-', {
				text : 'Export',
				icon : contextPath+'/common/images/icons/page_white_excel.png',
				action : 'export'
			}, '-', {
				text : 'MonthlyList',
				icon : contextPath+'/common/images/icons/page_white_excel.png',
				action : 'listMonthly'
			}, '-', {
				text : 'Filter',
				icon : contextPath+'/common/images/icons/find.png',
				action : 'search'
			},"->"];
		} else {
			this.items = [ {
				xtype : 'form',
				padding : '5 5 0 5',
				border : false,
				style : 'background-color: #fff;',
				layout : 'column',
				defaults : {
					border : false,
					layout : 'form',
					columnWidth : .17
				},
				items : [ {
					items : [ taskID ]
				}, {
					items : [ owner ]
				}, {
					items : [ status ]
				} ]
			} ];

			this.buttons = [ {
				text : 'Export',
				icon : contextPath+'/common/images/icons/page_white_excel.png',
				action : 'export'
			}, '-', {
				text : 'Filter',
				icon : contextPath+'/common/images/icons/find.png',
				action : 'search'
			},"->" ];
		}

		this.callParent(arguments);
	}
});
