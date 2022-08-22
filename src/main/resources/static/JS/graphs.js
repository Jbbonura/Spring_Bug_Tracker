const statusData = {
    'New' : newTicketStatusCount,
    'Open' : openTicketStatusCount,
    'In Progress' : inProgressTicketStatusCount,
    'Resolved' : resolvedTicketStatusCount,
    'Info Required' : infoRequiredTicketStatusCount,
    
};

const priorityData = {
	'None' : nonePriorityCount,
	'Low' : lowPriorityCount,
	'Med' : medPriorityCount,
	'High' : highPriorityCount,
};

const typeData = {
	'Other/Comments' : otherTypeCount,
	'Feature Requests' : requestTypeCount,
	'Bugs and Errors' : bugTypeCount,
};

const status = document.getElementById('statusChart').getContext('2d');

const statusChart = new Chart(status, {
	type: 'pie',
	data: {
		labels: Object.keys(statusData),
		datasets: [
			{
				data: Object.values(statusData),
			},
		],
	},
	options: {
		backgroundColor: [
			'red',
			'orange',
			'blue',
			'green',
			'yellow'
		]
	}
});

const priority = document.getElementById('priorityChart').getContext('2d');

const priorityChart = new Chart (priority, {
	type: 'bar',
	data: {
		labels: Object.keys(priorityData),
		datasets: [
			{
				label: '',
				data: Object.values(priorityData),
			},
		],
	},
	options: {
		indexAxis: 'y',
		backgroundColor: [
			'green',
			'blue',
			'yellow',
			'red'
		]
	}
});

const type = document.getElementById('typeChart').getContext('2d');

const typeChart = new Chart (type, {
	type: 'doughnut',
	data: {
		labels: Object.keys(typeData),
		datasets: [
			{
				data: Object.values(typeData),
			},
		],
	},
	options: {
		backgroundColor: [
			'green',
			'yellow',
			'red'
		]
	}
});