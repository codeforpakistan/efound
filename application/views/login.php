<!DOCTYPE html>
<html>
<?php $this->load->view('common/head'); ?>

<body class="login-page">
<!-- Start: Main -->
<div id="main">
  <div class="container">
    <div class="row">
      <div id="page-logo"> <img src="<?php echo base_url(); ?>/assets/img/logos/logo.png" class="img-responsive" alt="logo" /> </div>
    </div>
    <div class="row">
      <div class="panel">
        <div class="panel-heading">
          <div class="panel-title"> <i class="fa fa-lock"></i> Login </div>
           </div>
           <?php 
			//$message = $this->session->flashdata('login_message');
			if(!empty($message)){ ?>
            <div class="tag-container">
					<span class="tm-tag tm-tag-success" id="jSmJy_1" style="background-color:#fcf2f2; border-color:#dfb5b4; color:#b94a48; margin:10px 0px 0px 14px"><span><?php echo $message; ?></span><a href="#" class="tm-tag-remove" id="jSmJy_Remover_1" tagidtoremove="1"></a></span>
            </div>
			<?php }
			?>
           <?php echo validation_errors('<div class="error">', '</div>'); ?>
        <form class="cmxform" id="altForm" method="post" action="<?php echo site_url('login/verifylogin');?>"/>
          <div class="panel-body">
            <div class="form-group">
              <div class="input-group"> <span class="input-group-addon"><i class="fa fa-user"></i> </span>
                <input type="text" name="efus_cnic" class="form-control phone" maxlength="30" autocomplete="off" placeholder="User Name" value="<?php echo set_value('efus_cnic'); ?>"  />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group"> <span class="input-group-addon"><i class="fa fa-key"></i> </span>
                <input type="password" name="efus_password" class="form-control product" autocomplete="off" placeholder="Password" value="<?php echo set_value('efus_password'); ?>" />
              </div>
            </div>
          </div>
          <div class="panel-footer"> <span class="panel-title-sm pull-left" style="padding-top: 7px;"><a> Forgotten Password?</a></span>
            <div class="form-group margin-bottom-none">
              <input class="btn btn-primary pull-right" type="submit" value="Login" />
              <div class="clearfix"></div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<!-- End: Main --> 
<?php $this->load->view('common/footer');?>
</body>
</html>
