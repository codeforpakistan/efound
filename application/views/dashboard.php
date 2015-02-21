<!DOCTYPE html>
<html>
<?php $this->load->view('common/head'); ?>

<body class="dashboard">
<!-- Start: Theme Preview Pane -->
<div id="skin-toolbox">
  <div class="skin-toolbox-toggle"> <i class="fa fa-flask"></i> </div>
  <div class="skin-toolbox-panel">
    <h4 class="padding-left-sm">Theme Options</h4>
    <form id="skin-toolbox-form" />
      <div class="form-group">
        <label class="checkbox-inline">
          <input id="header-option" class="checkbox" type="checkbox" checked="" />
          Fixed <b>Header</b> </label>
      </div>
      <div class="form-group">
        <label class="checkbox-inline">
          <input id="sidebar-option" class="checkbox" type="checkbox" />
          Fixed <b>Sidebar</b> </label>
      </div>
      <div class="form-group">
        <label class="checkbox-inline option-disabled">
          <input id="breadcrumb-option" class="checkbox" type="checkbox" disabled="" />
          Fixed <b>Breadcrumbs</b> </label>
      </div>
      <hr class="short" style="margin: 7px 10px;" />
      <div class="form-group">
        <label class="checkbox-inline">
          <input id="breadcrumb-hidden" class="checkbox" type="checkbox" />
          Hide <b>Breadcrumbs</b> </label>
      </div>
      <div class="form-group margin-bottom-lg">
        <label class="checkbox-inline">
          <input id="searchbar-hidden" class="checkbox" type="checkbox" />
          Hide <b>Search Bar</b> </label>
      </div>
      <h4 class="padding-left-sm">Layout Options</h4>
      <div class="form-group">
        <label class="radio-inline">
          <input class="radio" type="radio" name="optionsRadios" id="fullwidth-option" checked="" />
          Fullwidth </label>
      </div>
      <div class="form-group">
        <label class="radio-inline">
          <input class="radio" type="radio" name="optionsRadios" id="boxed-option" />
          Boxed Layout</label>
      </div>
      <hr class="short" />
      <div class="form-group"> <a href="customizer.html" id="customizer-link" class="btn btn-warning btn-gradient btn-block padding-bottom padding-top">CUSTOMIZER</a> </div>
    </form>
  </div>
</div>
<!-- End: Theme Preview Pane --> 
	
<!-- Start: Header -->
		<?php $this->load->view('common/header'); ?>
<!-- End: Header --> 
<!-- Start: Main -->
<div id="main"> 
  <!-- Start: Sidebar -->
 		<?php $this->load->view('common/sidebar'); ?>
  <!-- End: Sidebar --> 
  <!-- Start: Content -->
  <section id="content">
    <div id="topbar">
      <ol class="breadcrumb">
        <li><a href="dashboard.html"><i class="fa fa-home"></i></a></li>
        <li><a href="index.html">Home</a></li>
        <li class="active">Dashboard</li>
      </ol>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div id="console-btns">
            <div class="row">
              <div class="col-xs-6 col-md-3">
                <div class="console-btn">
                  <div class="console-icon divider-right"> <span class="glyphicons glyphicons-cardio"></span> </div>
                  <div class="console-text">
                    <div class="console-title">Servers</div>
                    <div class="console-subtitle">View More <i class="fa fa-caret-right"></i> </div>
                  </div>
                </div>
              </div>
              <div class="col-xs-6 col-md-3">
                <div class="console-btn">
                  <div class="console-icon divider-right"> <span class="glyphicons glyphicons glyphicons-hdd"></span> </div>
                  <div class="console-text">
                    <div class="console-title">Database</div>
                    <div class="console-subtitle">View More <i class="fa fa-caret-right"></i> </div>
                  </div>
                </div>
              </div>
              <div class="col-xs-6 col-md-3">
                <div class="console-btn">
                  <div class="console-icon divider-right"> <span class="glyphicons glyphicons-print"></span> </div>
                  <div class="console-text">
                    <div class="console-title">Reports</div>
                    <div class="console-subtitle">View More <i class="fa fa-caret-right"></i> </div>
                  </div>
                </div>
              </div>
              <div class="col-xs-6 col-md-3">
                <div class="console-btn">
                  <div class="console-icon divider-right"> <span class="glyphicons glyphicons-security_camera"></span> </div>
                  <div class="console-text">
                    <div class="console-title">Utilities</div>
                    <div class="console-subtitle">View More <i class="fa fa-caret-right"></i> </div>
                  </div>
                </div>
              </div>
              <div class="col-lg-4" id="console-search-btn">
                <div class="console-btn">
                  <div class="console-icon divider-right"> <span class="glyphicons glyphicons-search"></span> </div>
                  <div class="console-filter">
                    <input type="text" id="filterSearch" class="search-bar form-control" placeholder="Filter Events Below..." />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <hr class="short" />
      <div class="row">
        <div class="col-md-8">
          <div class="row">
            <div class="col-md-12"> </div>
            <div class="col-md-12">
              <div class="panel">
                <div class="panel-heading">
                  <div class="panel-title"> <i class="fa fa-bar-chart-o"></i> Real Time Site Traffic</div>
                </div>
                <div class="panel-body">
                  <div class="chart-updating" style="height: 300px; width: 100%;"></div>
                  </div>
                  <div class="panel-footer">
          
                    <div class="row">
                      <div class="col-xs-4 col-sm-3 text-center">
                        <div class="chart-btn">
                          <div class="chart-icon"> <span class="sparkbar1 sparkline-delay" data-delay="300"></span> </div>
                          <div class="chart-text">
                            <div class="chart-title">5,231</div>
                            <div class="chart-subtitle">Hits</div>
                          </div>
                        </div>
                      </div>
                      <div class="col-xs-4 col-sm-3 text-center">
                        <div class="chart-btn">
                          <div class="chart-icon"> <span class="sparkbar2 sparkline-delay" data-delay="300"></span> </div>
                          <div class="chart-text">
                            <div class="chart-title">7,181</div>
                            <div class="chart-subtitle">Visits</div>
                          </div>
                        </div>
                      </div>
                      <div class="col-xs-4 col-sm-3 text-center">
                        <div class="chart-btn">
                          <div class="chart-icon"> <span class="sparkbar3 sparkline-delay" data-delay="300"></span> </div>
                          <div class="chart-text">
                            <div class="chart-title">2,462</div>
                            <div class="chart-subtitle">Sales</div>
                          </div>
                        </div>
                      </div>
                      <div class="hidden-xs col-sm-3 text-center">
                        <div class="chart-btn">
                          <div class="chart-icon"> <span class="sparkbar4 sparkline-delay" data-delay="300"></span> </div>
                          <div class="chart-text">
                            <div class="chart-title">1,385</div>
                            <div class="chart-subtitle">Users</div>
                          </div>
                        </div>
                      </div>
                    </div>
                </div>
              </div>
            </div>
            <div class="col-md-12">
              <div class="panel">
                <div class="panel-heading">
                  <div class="panel-title"> <i class="fa fa-pencil"></i> Recent Activity </div>
                  <ul class="nav panel-tabs">
                    <li class="active"><a href="#tab1" data-toggle="tab">Tasks</a></li>
                    <li><a href="#tab2" data-toggle="tab">Tickets</a></li>
                    <li><a href="#tab3" data-toggle="tab">Comments</a></li>
                  </ul>
                </div>
                <div class="panel-body">
                  <div class="tab-content padding-none border-none">
                    <div id="tab1" class="tab-pane active">
                      <table class="table table-widget table-striped table-checklist" id="datatable">
                        <thead>
                          <tr>
                            <th>Task</th>
                            <th>Progress</th>
                            <th>Skills</th>
                            <th>Notes</th>
                            <th>Deadline</th>
                            <th></th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <td class="text-slash">Test Building presentation <b>Capacity</b></td>
                            <td><div class="progress">
                                <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%"><span class="sr-only">40% Complete (success)</span> </div>
                              </div></td>
                            <td><span class="label btn-dark">Patience</span></td>
                            <td class="text-slash text-muted"><small>400 people will attend</small></td>
                            <td class="text-slash semi-bold">11/14/2013</td>
                            <td class="text-right"><input class="checkbox row-checkbox" type="checkbox" /></td>
                          </tr>
                          <tr>
                            <td class="text-slash">Write check to <b>Kids Hospital</b> for Holiday</td>
                            <td><div class="progress">
                                <div class="progress-bar progress-bar-alert" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%"><span class="sr-only">40% Complete (success)</span> </div>
                              </div></td>
                            <td><span class="label btn-blue">A Heart</span></td>
                            <td class="text-slash text-muted"><small>Amount is still $4,500</small></td>
                            <td class="text-slash semi-bold">11/14/2013</td>
                            <td class="text-right"><input class="checkbox row-checkbox" type="checkbox" /></td>
                          </tr>
                          <tr>
                            <td class="text-slash"><b>Upload</b> all 1400 Icons to Server</td>
                            <td><div class="progress">
                                <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%"><span class="sr-only">80% Complete</span> </div>
                              </div></td>
                            <td><span class="label btn-orange2">Python</span><span class="label btn-alert">DB</span></td>
                            <td class="text-slash text-muted"><small>400 people will attend</small></td>
                            <td class="text-slash semi-bold">11/14/2013</td>
                            <td class="text-right"><input class="checkbox row-checkbox" type="checkbox" /></td>
                          </tr>
                          <tr>
                            <td class="text-slash">Restyle <b>Themeforest</b> website design</td>
                            <td><div class="progress">
                                <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%"><span class="sr-only">60% Complete (warning)</span> </div>
                              </div></td>
                            <td><span class="label btn-blue2">CSS</span><span class="label btn-green">Html</span></td>
                            <td class="text-slash text-muted"><small>400 people will attend</small></td>
                            <td class="text-slash semi-bold">11/14/2013</td>
                            <td class="text-right"><input class="checkbox row-checkbox" type="checkbox" /></td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                    <div id="tab2" class="tab-pane">
                      <table class="table table-striped table-widget table-checklist" id="datatable_2">
                        <thead>
                          <tr>
                            <th>Status</th>
                            <th>Date</th>
                            <th>Description</th>
                            <th>Assigned To</th>
                            <th>Number</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <td><span class="label label-primary">PHP</span></td>
                            <td>10/19/2013</td>
                            <td>Develop <b>Prototype</b> for amazon matrix</td>
                            <td>Ashley Time</td>
                            <td><b>[#4311525]</b></td>
                          </tr>
                          <tr>
                            <td><span class="label label-primary">Utility</span></td>
                            <td>11/14/2013</td>
                            <td>Convert Frontend to <b>Bootstrap 3</b></td>
                            <td>Roger Wins</td>
                            <td><b>[#1531275]</b></td>
                          </tr>
                          <tr>
                            <td><span class="label label-primary">CSS</span></td>
                            <td>01/03/2014</td>
                            <td>Re-Style client <b>Header</b> for release</td>
                            <td>Kevin Heart</td>
                            <td><b>[#6233255]</b></td>
                          </tr>
                          <tr>
                            <td><span class="label label-primary">HTML</span></td>
                            <td>10/29/2013</td>
                            <td>Create new <b>Accordion</b> for FAQ</td>
                            <td>Jacop Parker</td>
                            <td><b>[#5332275]</b></td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                    <div id="tab3" class="tab-pane chat-panel">
                      <div class="media margin-top-sm">
                        <div class="media-img pull-left"> <img src="img/avatars/2.png" class="img-responsive" alt="avatar" /> </div>
                        <div class="media-body">
                          <div class="media-content">
                            <h4 class="media-heading">Marry Wilconson</h4>
                            <p class="media-timestamp">January 12, 2013 at 1:38 pm</p>
                            Cras sit amet nibh libero, in grio, vestibulum in vulputate at,lla. Donec lacinia congue felis in faucibus. </div>
                        </div>
                      </div>
                      <div class="media">
                        <div class="media-img pull-left"> <img src="img/avatars/1.png" class="img-responsive" alt="avatar" /> </div>
                        <div class="media-body">
                          <div class="media-content">
                            <h4 class="media-heading">Todd Philips</h4>
                            <p class="media-timestamp">January 12, 2013 at 1:38 pm</p>
                            Cras sit amet nibh libero, in gravida nulla. Nulla vnec lacinia congue felis in faucibus.. Nulla vnec lacinia congue felis in faucibus. </div>
                          <div class="media margin-bottom">
                            <div class="media-img pull-left"> <img src="img/avatars/6.png" class="img-responsive" alt="avatar" /> </div>
                            <div class="media-body">
                              <div class="media-content">
                                <h4 class="media-heading">Roger Awesome</h4>
                                <p class="media-timestamp">January 12, 2013 at 1:38 pm</p>
                                Cras sit amet nibh libero,pus viverra turpis. Fuselis in faucibus. </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-12">
              <div class="panel">
                <div class="panel-heading">
                  <div class="panel-title"> <i class="fa fa-bar-chart-o"></i> Calendar</div>
                </div>
                <div class="panel-body">
                  <div id='calendar'></div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="row">
            <div class="col-md-12 visible-md visible-lg" id="timeline-widget">
              <input class="hidden search form-control" />
              <ul class="timeline-widget list">
                <li class="media">
                  <div class="timeline-icon"><span class="glyphicons glyphicons-cloud-download text-red"></span> </div>
                  <div class="media-body"> <b>Garry</b> edited project <b>Titans</b> <span class="date"> - 2 hours ago</span> </div>
                </li>
                <li class="media">
                  <div class="timeline-icon"><span class="glyphicons glyphicons-certificate text-blue2"></span> </div>
                  <div class="media-body"> <b>Cynthia</b> added <b>9 images</b><span class="date"> - 4 hours ago</span> </div>
                </li>
                <li class="media">
                  <div class="timeline-icon"><span class="glyphicons glyphicons-bell text-alert"></span> </div>
                  <div class="media-body"> <b>Roger</b> commented on <b>Titan</b><span class="date"> - 9 hours ago</span> </div>
                </li>
                <li class="media">
                  <div class="timeline-icon"><span class="glyphicons glyphicons-pin text-red2"></span> </div>
                  <div class="media-body"> <b>Garry</b> modified his <b>account</b><span class="date"> - 22 hours ago</span> </div>
                </li>
                <li class="media">
                  <div class="timeline-icon"><span class="glyphicons glyphicons-keys text-green2"></span> </div>
                  <div class="media-body"> <b>Rick</b> gained <b>Admin</b> powers<span class="date"> - 1 day ago</span> </div>
                </li>
                <li class="media">
                  <div class="timeline-icon"><span class="glyphicons glyphicons-ear_plugs text-alert"></span> </div>
                  <div class="media-body"> <b>Mary</b> changed her <b>contact</b><span class="date"> - 1 day ago</span> </div>
                </li>
                <li class="media">
                  <div class="timeline-icon"><span class="glyphicons glyphicons-flash text-orange4"></span> </div>
                  <div class="media-body"> <b>Tinsley</b> uploaded <b>Titan</b> files<span class="date"> - 2 days ago</span> </div>
                </li>
                <li class="media">
                  <div class="timeline-icon"><span class="glyphicons glyphicons-envelope text-blue3"></span> </div>
                  <div class="media-body"> <b>Todd</b> spent 14 hours <b>SEO Task</b> <span class="date"> - 3 days ago</span> </div>
                </li>
              </ul>
            </div>
            <div class="col-md-12 message-widget" style="margin-top:20px">
              <div class="panel">
                <div class="panel-heading">
                  <div class="panel-title"> <i class="fa fa-envelope"></i> Message Widget</div>
                  <div class="messenger-header-actions pull-right">
                    <div class="mini-action-icons margin-right-sm"></div>
                    <button type="button" class="btn btn-default btn-gradient dropdown-toggle" data-toggle="dropdown"> <span class="glyphicons glyphicons-direction"></span> Send</button>
                  </div>
                </div>
                <div class="panel-body">
                  <input type="text" class="form-control text-field-alt margin-bottom" placeholder="Search for users with @" />
                  <textarea class="form-control text-field-alt margin-bottom-lg" rows="3" placeholder="You can type your message here..."></textarea>
                  <div class="table-sorted">
                    <table class="table table-striped">
                      <tbody>
                        <tr>
                          <td class="avatar"><img src="img/avatars/2.png" class="img-responsive" alt="avatar" /></td>
                          <td><i class="fa fa-circle text-green"></i></td>
                          <td><b>Cynthia Rodche</b></td>
                          <td class="small text-muted hidden-md">CEO</td>
                          <td class="text-right"><div class="btn-group">
                              <button type="button" class="btn btn-default btn-gradient dropdown-toggle" data-toggle="dropdown"> <span class="glyphicons glyphicons-cogwheel"></span> View</button>
                              <ul class="dropdown-menu checkbox-persist pull-right text-left" role="menu">
                                <li><a><i class="fa fa-user"></i> View Profile </a></li>
                                <li><a><i class="fa fa-envelope-o"></i> Message </a></li>
                              </ul>
                            </div></td>
                        </tr>
                        <tr>
                          <td class="avatar"><img src="img/avatars/7.png" class="img-responsive" alt="avatar" /></td>
                          <td><i class="fa fa-circle text-red"></i></td>
                          <td><b>Cynthia Rodche</b></td>
                          <td class="small text-muted hidden-md">Creative</td>
                          <td class="text-right"><div class="btn-group">
                              <button type="button" class="btn btn-default btn-gradient dropdown-toggle" data-toggle="dropdown"> <span class="glyphicons glyphicons-cogwheel"></span> View</button>
                              <ul class="dropdown-menu checkbox-persist pull-right text-left" role="menu">
                                <li><a><i class="fa fa-user"></i> View Profile </a></li>
                                <li><a><i class="fa fa-envelope-o"></i> Message </a></li>
                              </ul>
                            </div></td>
                        </tr>
                        <tr>
                          <td class="avatar"><img src="img/avatars/6.png" class="img-responsive" alt="avatar" /></td>
                          <td><i class="fa fa-circle text-green"></i></td>
                          <td><b>Cynthia Rodche</b></td>
                          <td class="small text-muted hidden-md">CEO</td>
                          <td class="text-right"><div class="btn-group">
                              <button type="button" class="btn btn-default btn-gradient dropdown-toggle" data-toggle="dropdown"> <span class="glyphicons glyphicons-cogwheel"></span> View</button>
                              <ul class="dropdown-menu checkbox-persist pull-right text-left" role="menu">
                                <li><a><i class="fa fa-user"></i> View Profile </a></li>
                                <li><a><i class="fa fa-envelope-o"></i> Message </a></li>
                              </ul>
                            </div></td>
                        </tr>
                        <tr>
                          <td class="avatar"><img src="img/avatars/3.png" class="img-responsive" alt="avatar" /></td>
                          <td><i class="fa fa-circle text-green"></i></td>
                          <td><b>Cynthia Rodche</b></td>
                          <td class="small text-muted hidden-md">Advisor</td>
                          <td class="text-right"><div class="btn-group">
                              <button type="button" class="btn btn-default btn-gradient dropdown-toggle" data-toggle="dropdown"> <span class="glyphicons glyphicons-cogwheel"></span> View</button>
                              <ul class="dropdown-menu checkbox-persist pull-right text-left" role="menu">
                                <li><a><i class="fa fa-user"></i> View Profile </a></li>
                                <li><a><i class="fa fa-envelope-o"></i> Message </a></li>
                              </ul>
                            </div></td>
                        </tr>
                        <tr>
                          <td class="avatar"><img src="img/avatars/1.png" class="img-responsive" alt="avatar" /></td>
                          <td><i class="fa fa-circle text-red"></i></td>
                          <td><b>Cynthia Rodche</b></td>
                          <td class="small text-muted hidden-md">Creative</td>
                          <td class="text-right"><div class="btn-group">
                              <button type="button" class="btn btn-default btn-gradient dropdown-toggle" data-toggle="dropdown"> <span class="glyphicons glyphicons-cogwheel"></span> View</button>
                              <ul class="dropdown-menu checkbox-persist pull-right text-left" role="menu">
                                <li><a><i class="fa fa-user"></i> View Profile </a></li>
                                <li><a><i class="fa fa-envelope-o"></i> Message </a></li>
                              </ul>
                            </div></td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- End: Content --> 
</div>
<!-- End: Main --> 

<?php $this->load->view('common/footer');?>
</body>
</html>
