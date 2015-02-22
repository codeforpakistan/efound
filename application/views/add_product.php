<!DOCTYPE html>
<html>
<?php $this->load->view('common/head'); ?>

<body class="dashboard">
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
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="row">
            <div class="col-md-12">
              <div class="panel">
                <?php if(!empty($message)){ ?>
                <div class="tag-container"> <span class="tm-tag tm-tag-success" id="jSmJy_1"><span><?php echo $message; ?></span><a href="#" class="tm-tag-remove" id="jSmJy_Remover_1" tagidtoremove="1">x</a></span> </div>
                <?php }
			?>
                <div class="panel-heading">
                  <div class="panel-title"> <i class="fa fa-pencil"></i> Add Product </div>
                </div>
                <div class="panel-body"> <?php echo validation_errors(); ?>
                  <form enctype="multipart/form-data" class="cmxform" id="signupForm" method="post" action="<?php echo site_url('dashboard/save_product');?>">
                    <div class="form-group">
                      <input  name="efpr_nfcid" required type="text" class="form-control" placeholder="NFC ID" >
                    </div>
                    <div class="form-group">
                      <input  name="efpr_name" required type="text" class="form-control" placeholder="Name" >
                    </div>
                    <div class="form-group">
                      <input  name="efpr_category" type="text" class="form-control" placeholder="Category" >
                    </div>
                    <div class="form-group">
                      <input  name="efpr_type" type="text" class="form-control" placeholder="Type" >
                    </div>
                    <div class="form-group">
                      <input  name="efpr_madeby" type="text" class="form-control" placeholder="Made By" >
                    </div>
                    <div class="form-group">
                      <input  name="efpr_model" type="text" class="form-control" placeholder="Model" >
                    </div>
                    <div class="form-group">
                      <input  name="efpr_makeyear" type="text" class="form-control"  placeholder="Make Year"  />
                    </div>
                    <div class="form-group">
                      <input  name="efpr_purchased" type="text" class="form-control" placeholder="Purchased"  />
                    </div>
                    <div class="form-group">
                      <input  name="efpr_details" type="text" class="form-control" placeholder="Product details"  />
                    </div>
                    <div class="form-group">
                      <input  name="efpr_cnic" type="text" class="form-control" placeholder="CNIC"  />
                    </div>
                    <div class="form-group">
                      <input class="btn btn-info btn-gradient btn-sm" name="submit" type="submit" value="Submit"  />
                    </div>
                  </form>
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
