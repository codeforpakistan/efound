<!-- Core Javascript - via CDN -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script> 
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script> 

<!-- Plugins - Via Local Storage -->
<script type="text/javascript" src="<?php echo base_url(); ?>/assets/vendor/plugins/jqueryflot/jquery.flot.min"></script>
<script type="text/javascript" src="<?php echo base_url(); ?>/assets/vendor/plugins/sparkline/jquery.sparkline.min.js"></script>
<script type="text/javascript" src="<?php echo base_url(); ?>/assets/vendor/plugins/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<?php echo base_url(); ?>/assets/vendor/plugins/calendar/fullcalendar.min.js"></script>
<!-- Plugins -->
<script type="text/javascript" src="<?php echo base_url(); ?>/assets/vendor/plugins/calendar/gcal.js"></script><!-- Calendar Addon -->
<script type="text/javascript" src="<?php echo base_url(); ?>/assets/vendor/plugins/jqueryflot/jquery.flot.resize.min.js"></script><!-- Flot Charts Addon -->
<script type="text/javascript" src="<?php echo base_url(); ?>/assets/vendor/plugins/datatables/js/datatables.js"></script><!-- Datatable Bootstrap Addon -->

<!-- Theme Javascript -->	
<script type="text/javascript" src="<?php echo base_url(); ?>/assets/js/uniform.min.js"></script>  
<script type="text/javascript" src="<?php echo base_url(); ?>/assets/js/main.js"></script>
<script type="text/javascript" src="<?php echo base_url(); ?>/assets/js/custom.js"></script>
<script type="text/javascript">
  jQuery(document).ready(function() {
	Core.init();
	
		// Init Datatables
	/*$('#datatable, #datatable_2').dataTable( {
	  "bSort": true,
	  "bPaginate": false,
	  "bLengthChange": false,
	  "bFilter": false,
	  "bInfo": false,
	  "bAutoWidth": false,
	  "aoColumnDefs": [{ 'bSortable': false, 'aTargets': [ -1 ] }]
	});
	
	// Init Sparkline Plugin
 	var runSparkline = function () {
		
		// Define Sparklines values
		var Values1 = [0,1,5,2,4,3,5,8,7];
		var Values2 = [3,2,3,1,0,2,5,6,4];
		var Values3 = [6,4,5,3,4,2,1,2,3];
		var Values4 = [2,1,2,3,2,4,2,1,0];

	    // Pass values, define options, and create chart
		$('.sparkbar1').sparkline(Values1, {type: "bar",
				barColor: "#428bca",
				barWidth: "8",
				height: "35"} 
		);
		$('.sparkbar2').sparkline(Values2, {type: "bar",
				barColor: "#5cb85c",
				barWidth: "8",
				height: "35"} 
		);
		$('.sparkbar3').sparkline(Values3, {type: "bar",
				barColor: "#5bc0de",
				barWidth: "8",
				height: "35"} 
		);
		$('.sparkbar4').sparkline(Values4, {type: "bar",
				barColor: "#777777",
				barWidth: "8",
				height: "35"} 
		);
		
		// Quick function to animate the appearance of Sparklines
		// Only ran on Sparkline elements which have the 
		// ".sparkline-delay" class and a set data-delay attr
		(function() {
			$('.sparkline-delay').each(function() {
				var This = $(this)
				var delayTime = $(this).data('delay');
	
				$(This).children('canvas').css({"height": "0", "vertical-align": "bottom"});
				var delayCharts = setTimeout(function () {
					  $(This).children('canvas').animate({height: 35}, 1800);
				}, delayTime); 
			});
		})();		
  	}

	// Init Calendar Plugin
	var runFullCalendar = function () {
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		
		// Define Calendar options and events
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			editable: true,
			events: [
				{
					title: 'All Day Event',
					start: new Date(y, m, 9),
					color: '#008aaf '
				},
				{
					title: 'Long Event',
					start: new Date(y, m, d-5),
					end: new Date(y, m, d-3)
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: new Date(y, m, d+3, 16, 0),
					allDay: false
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: new Date(y, m, d+10, 16, 0),
					allDay: false
				},
				{
					title: 'Meeting',
					start: new Date(y, m, d, 10, 30),
					allDay: false,
					color: '#0070ab'
				},
				{
					title: 'Lunch',
					start: new Date(y, m, d, 12, 0),
					end: new Date(y, m, d, 14, 0),
					allDay: false,
					color: '#0070ab'
				},
				{
					title: 'Birthday Party',
					start: new Date(y, m, d+1, 19, 0),
					end: new Date(y, m, d+1, 22, 30),
					allDay: false
				},
				{
					title: 'Mandatory!',
					start: new Date(y, m, 22),
					color: '#d10011'
				}
			]
		});
	}

	// Init Flot Charts Plugin
	var runFlotCharts = function () {
		
	// Define chart clolors ( add more colors if you want or flot will do it automatically
	var chartColours = ['#5bc0de'];

	// Generate random number for charts
	randNum = function(){return (Math.floor( Math.random()* (1+40-20) ) ) + 20;}

	// Check if element exist and draw auto updating chart
	if($(".chart-updating").length) {
		$(function () {
			// We use an inline data source in the example, usually data would
			// be fetched from a server
			var data = [], totalPoints = 50;
			function getRandomData() {
				if (data.length > 0)
					data = data.slice(1);

				while (data.length < totalPoints) {
					var prev = data.length > 0 ? data[data.length - 1] : 50;
					var y = prev + Math.random() * 10 - 5;
					if (y < 0)
						y = 0;
					if (y > 100)
						y = 100;
					data.push(y);
				}

				var res = [];
				for (var i = 0; i < data.length; ++i)
					res.push([i, data[i]])
				return res;
			}

			var updateInterval = 750;

			var options = {

				series: { 
					shadowSize: 0, // drawing is faster without shadows
					lines: {
						show: true,
						fill: true,
						fillColor: { colors: ['rgba(47, 137, 214, 0.4)', 'rgba(98, 174, 239, 0.3)'] },
						lineWidth: 1,
						steps: false
					},
					points: {
						show: false,
						radius: 2.5,
						symbol: "circle",
						lineWidth: 2.5
					}
				},
				grid: {
					show: true,
					aboveData: false,
					color: "#3f3f3f",
					labelMargin: 5,
					axisMargin: 0, 
					borderWidth: 0,
					borderColor:null,
					minBorderMargin: 5 ,
					clickable: true, 
					hoverable: true,
					autoHighlight: false,
					mouseActiveRadius: 20
				}, 
				colors: chartColours,
				yaxis: { 
					 min: 0,
					 max: 100,
					 font: {size: 11,	color: "#888888"}
				},
				xaxis: { 
					 show: true,
					 font: {size: 11,	color: "#888888"}
				}
			};
			var plot = $.plot($(".chart-updating"), [ getRandomData() ], options);

			function update() {
				plot.setData([ getRandomData() ]);
				// since the axes don't change, we don't need to call plot.setupGrid()
				plot.draw();
				
				setTimeout(update, updateInterval);
			}
			update();
		});
	  }
  }
     
  runSparkline();
  runFullCalendar();	  
  runFlotCharts();*/
});
</script>