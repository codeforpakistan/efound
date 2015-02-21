<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Login extends CI_Controller {

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
		  $this->load->library('form_validation');
		  $this->load->model('login_model');
		  $this->load->library('session');
	  }
	  
	  public function index()
	  {
		  $session_data = $this->session->userdata('logged_in');
		  if (isset($session_data['Id'])){
				redirect('dashboard', 'refresh');
		  }
		  $this->load->view('login');
	  }
	  public function verifylogin($message=''){	
		  $this->form_validation->set_rules('efus_cnic', 'CNIC', 'required');
		  $this->form_validation->set_rules('efus_cnic', 'Password', 'required');
			  if ($this->form_validation->run() == FALSE)
			  {
				  $data['message']	= $message;
				  $this->load->view('login',$data);
			  }
			  else
			  {
				   $username = $this->input->post('efus_cnic');
				   $password = $this->input->post('efus_cnic');
				   $password = md5( $password );
				   $status = $this->login_model->validate_login($username,$password); 
				   if(!empty($status)){
						foreach($status as $row){ //echo $row->efus_cnic;exit; 
							//add all data to session
							$sess_array = array(
								'Id' => $row->efus_id,
								'CNIC' => $row->efus_cnic,
								'Type' => $row->type
		
							);	
							//print_r($sess_array);exit;
							$this->session->set_userdata('logged_in', $sess_array);
						}
						if($this->session->userdata('logged_in'))
						{
							$session_data = $this->session->userdata('logged_in');
							//if ($session_data['Type']=='Admin')
							//{
							   //$data['page']	= 'dashboard';
							   //$this->load->view('dashboard',$data);
							   redirect('dashboard', 'refresh');
							//}							
						}
					}
					else{
							$message = "You Have Entered wrong Credentials";
							$data['message']	= $message;
							
							//$this->session->set_flashdata('login_message', 'Successfully deleted');
							//redirect('admin/login', 'refresh');
							
							$this->load->view('login',$data);
					}
				  
			  }
		  
		  
	  }
	  function logout(){
			$this->session->sess_destroy();
			$this->load->view('login');
      }
}

/* End of file welcome.php */
/* Location: ./application/controllers/welcome.php */