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
                <div class="panel-heading">
                  <div class="panel-title"> <i class="fa fa-table"></i> All Products </div>
                  <a href="<?php echo site_url("dashboard/add_product"); ?>">
                  <button style="float: right" type="button" 
                    class="btn btn-info btn-gradient btn-sm">Add Product</button>
                  </a> </div>
                <?php 
				$message = $this->session->flashdata('update_message_fail');
				if(!empty($message)){ ?>
					<div class="tag-container"> <span class="tm-tag tm-tag-success" id="jSmJy_1" style="background-color:#fcf2f2; border-color:#dfb5b4; color:#b94a48"><span><?php echo $message; ?></span><a href="#" class="tm-tag-remove" id="jSmJy_Remover_1" tagidtoremove="1"></a></span> </div>
                <?php } ?>
                <?php 
				$message = $this->session->flashdata('add_message');
				if(!empty($message)){ ?>
					<div class="tag-container"> <span class="tm-tag tm-tag-success" id="jSmJy_1"><span><?php echo $message; ?></span><a href="#" class="tm-tag-remove" id="jSmJy_Remover_1" tagidtoremove="1"></a></span> </div>
                <?php } ?>
                <?php 
				$message = $this->session->flashdata('update_message');
				if(!empty($message)){ ?>
					<div class="tag-container"> <span class="tm-tag tm-tag-success" id="jSmJy_1"><span><?php echo $message; ?></span><a href="#" class="tm-tag-remove" id="jSmJy_Remover_1" tagidtoremove="1"></a></span> </div>
                <?php } ?>
                <?php 
				$message = $this->session->flashdata('delete_message');
				if(!empty($message)){ ?>
					<div class="tag-container"> <span class="tm-tag tm-tag-success" id="jSmJy_1"><span><?php echo $message; ?></span><a href="#" class="tm-tag-remove" id="jSmJy_Remover_1" tagidtoremove="1"></a></span> </div>
                <?php } ?>
                <?php if(!empty($all_products)){ ?>
                <div class="panel-body">
                  <div class="table-responsive">
                    <table width="100%" class="table table-bordered">
                      <thead>
                        <tr>
                          <th width="80%">Name</th>
                          <th width="10%">Status</th>
                          <th style="text-align:center">Action</th>
                        </tr>
                      </thead>
                      <tbody>
                        <?php foreach($all_products as $all_products_row) { ?>
                        <tr>
                          <td><?php echo $all_products_row->efpr_name; ?></td>
                          <td><?php if ( $all_products_row->efpr_status == 'stolen'){ ?>
                          		 <a onClick="return confirm('Are You Sure To its found ?')" href="<?php echo site_url("admin/domain/update/".$all_products_row->efpr_id); ?>">
                                <button type="button" class="btn btn-info btn-gradient btn-xs" style="background-color:#D20505; border-color:#DE4747">Stolen</button>
                                </a>
                          	<?php }elseif( $all_products_row->efpr_status == 'found'){ ?>
                                <a onClick="return confirm('Are You Sure To its Stolen ?')" href="<?php echo site_url("admin/domain/update/".$all_products_row->efpr_id); ?>">
                                <button type="button" class="btn btn-info btn-gradient btn-xs" style="background-color:#01DF01; border-color:#01DF01">Found</button>
                                </a>
                            <?php }else{ ?>
                            	<a onClick="return confirm('Are You Sure To its Assign ?')" href="<?php echo site_url("admin/domain/update/".$all_products_row->efpr_id); ?>">
                                <button type="button" class="btn btn-info btn-gradient btn-xs">Assign</button>
                                </a>
                            <?php } ?>
                          </td>
                          <td width="19%"><a  href="<?php echo site_url("admin/domain/edit/".$all_products_row->efpr_id); ?>">
                            <button  type="button" class="btn btn-info btn-gradient btn-xs">Edit</button>
                            </a> <a onClick="return confirm('Are You Sure To Delete ?')" href="<?php echo site_url("dashboard/delete/".$all_products_row->efpr_id); ?>">
                            <button type="button" class="btn btn-info btn-gradient btn-xs" style="background-color:#D20505; border-color:#DE4747">Delete</button>
                            </a></td>
                        </tr>
                        <?php  }
				  
				  ?>
                      </tbody>
                    </table>
                    <p class="paginate"><?php //echo $links; ?></p>
                  </div>
                </div>
                <?php } else { echo "No Record Found"; } ?>
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
