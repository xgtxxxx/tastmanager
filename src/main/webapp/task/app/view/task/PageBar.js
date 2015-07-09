Ext.define('Task.view.task.PageBar', {
	extend : 'Ext.toolbar.Paging',
	alias : 'widget.pagebar',

	title : 'Edit Task',
	layout : 'fit',
	submitUrl : '',
	autoShow : true,
	width : 600,
	buttonAlign : 'center',
	initComponent : function() {
		this.items = [ {
			xtype : 'form',
			url : this.submitUrl,
			padding : '5 5 0 5',
			border : false,
			style : 'background-color: #fff;',
			layout : 'column',
			defaults : {
				border : false
			},
			items : [
					{
						columnWidth : .5,
						layout : 'form',
						items : [
								{
									xtype : 'hidden',
									name : 'id'

								},
								{
									xtype : 'textfield',
									name : 'taskId',
									allowBlank : false,
									fieldLabel : 'Task ID'
								},
								{
//									name : 'priority',
//									minValue : 1,
//									allowBlank : false,
//									fieldLabel : 'Priority',
//									xtype : 'combo',
//									editable : false,
//									displayField : 'field1',
//									valueField : 'field2',
//									hiddenName : 'Priority',
//									store : [ [ 1, 'High' ], [ 2, 'MED' ],
//											[ 3, 'Low' ], [ 4, 'Tiny' ] ],
//									queryMode : 'local'
									
									xtype : 'textfield',
									name : 'releaseVersion',
									allowBlank : false,
									fieldLabel : 'Release Version'
								}, {
									xtype : 'datefield',
									name : 'assignDate',
									allowBlank : false,
									format : 'Y-m-d',
									fieldLabel : 'Assigned Date'
								}]
					}, {
						columnWidth : .5,
						layout : 'form',
						items : [ {
							xtype : 'combo',
							name : 'status',
							fieldLabel : 'Status',
							allowBlank : false,
							editable : false,
							displayField : 'description',
							valueField : 'id',
							hiddenName : 'status',
							store : 'Status',
							queryMode : 'remote'
						}, {
							xtype : 'numberfield',
							name : 'progress',
							fieldLabel : 'Progress(%)'
						}, {
							xtype : 'datefield',
							name : 'fixedDate',
							format : 'Y-m-d',
							fieldLabel : 'Fixed Date'
						} ]
					}, {
						columnWidth : 1,
						layout : 'form',
						items : [ {
							xtype : 'textareafield',
							name : 'description',
							grow : true,
							fieldLabel : 'Description',
							anchor : '100%'
						}, {
							xtype : 'textareafield',
							name : 'commentsAndQuestions',
							grow : true,
							fieldLabel : 'Comments/<br/>Questions',
							anchor : '100%'
						}, {
							xtype : 'textareafield',
							name : 'feedback',
							fieldLabel : 'Feedback',
							anchor : '100%'
						}]
					} ]
		} ];

		this.buttons = [ {
			text : 'Save',
			action : 'save'
		}, {
			text : 'Cancel',
			scope : this,
			handler : this.close
		} ];

		this.callParent(arguments);
	}
});
