<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Dashboard extends CI_Controller {

	/**
	 * Index Page for this controller.
	 *
	 * Maps to the following URL
	 * 		http://example.com/index.php/welcome
	 *	- or -  
	 * 		http://example.com/index.php/welcome/index
	 *	- or -
	 * Since this controller is set as the default controller in 
	 * config/routes.php, it's displayed at http://example.com/
	 *
	 * So any other public methods not prefixed with an underscore will
	 * map to /index.php/welcome/<method_name>
	 * @see http://codeigniter.com/user_guide/general/urls.html
	 */
	 function __construct()
	 {
		  parent::__construct();
		  $session_data = $this->session->userdata('logged_in');
		  $this->load->model('product_model');
		  $this->load->library('form_validation');
		  $this->load->library('pagination');
		  if (!isset($session_data['Id'])){
				//redirect('dashboard', 'refresh');
			   $this->session->set_flashdata('login_message', 'Please login To Continue');
			   redirect('login', 'refresh');
		  }
	 }
	 public function index()
	 {
		 $data = array(
		 'title' => 'My Tagged Products'
		 );
		 $this->load->view('dashboard/products',$data);
	 }
	 public function products() {
		$data["all_products"] = $this->product_model->get_products();
		//$data['message']	= $message;
		$data['title'] ='My Tagged Products';
		$this->load->view('products',$data);
	 }
	 public function add_product() {
		//$data["all_products"] = $this->product_model->get_products();
		//$data['message']	= $message;
		$data['title'] ='Add Product';
		$this->load->view('add_product',$data);
	 }
	 public function delete($id = 0) {
		$this->product_model->delete($id);
		$this->session->set_flashdata('delete_message', 'Successfully deleted');
		redirect('dashboard/products', 'refresh');
	 }
	 public function save_product() {
		$this->form_validation->set_rules('efpr_nfcid', 'NFC ID', 'required');
		$this->form_validation->set_rules('efpr_name', 'Name', 'required');
		if ($this->form_validation->run() == FALSE)
			{
			    $this->load->view('dashboard/add_product',$data);
				return;
			}
			else
			{ 
				 $efpr_nfcid  		= $this->input->post('efpr_nfcid');
				 $efpr_name   		= $this->input->post('efpr_name');
				 $efpr_category 	= $this->input->post('efpr_category');
				 $efpr_type 		= $this->input->post('efpr_type');
				 $efpr_madeby 		= $this->input->post('efpr_madeby');
				 $efpr_model 		= $this->input->post('efpr_model');
				 $efpr_makeyear 	= $this->input->post('efpr_makeyear');
				 $efpr_purchased 	= $this->input->post('efpr_purchased');
				 $efpr_details 		= $this->input->post('efpr_details');
				 $efpr_cnic 		= $this->input->post('efpr_cnic');
				 $product=array(
					  'efpr_nfcid' 		=> $efpr_nfcid,
					  'efpr_name' 		=> $efpr_name,
					  'efpr_category' 	=> $efpr_category,
					  'efpr_category' 	=> $efpr_category,
					  'efpr_madeby' 	=> $efpr_madeby,
					  'efpr_model' 		=> $efpr_model,
					  'efpr_makeyear' 	=> $efpr_makeyear,
					  'efpr_purchased' 	=> $efpr_purchased,
					  'efpr_details' 	=> $efpr_details,
					  'efpr_cnic' 		=> $efpr_cnic,
				  );
				
				//echo "<pre>";print_r($product); die;
				
				$data = $this->product_model->save($product);
				//$message="Successfully Added";
				//$this->add_domain($message);
				$this->session->set_flashdata('add_message', 'Successfully Added');
				redirect('dashboard/products', 'refresh');
			}
	 }
}

/* End of file welcome.php */
/* Location: ./application/controllers/welcome.php */