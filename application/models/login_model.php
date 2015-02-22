<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Login_model extends CI_Model {

	public function __construct()
	{
		parent::__construct();
	}
	function validate_login($username,$password) {
		$this->db->where("efus_cnic",$username);
        $this->db->where("efus_password",$password);
		$query = $this->db->get("efound_users");
		
		if($query->num_rows() == 0)
		{
   			return false;
 		} 
 
 		if ($query->num_rows() > 0) {
			foreach ($query->result() as $row) {
				$data[] = $row;
			}
			return $data;
        }
        return false;
	}
	
	function checkuseremail_exist($email){
	 $this->db->where("email",$email);
     $query=$this->db->get("customer");
	 //echo $this->db->query(); die;
	// echo $this->db->last_query();exit();
	  if($query->num_rows()>0)
        {
		//echo $query->num_rows();
		return TRUE;
		}else{
		return FALSE;
		}
	
	}
	function save($c_data){
		
        $this->db->insert('customer', $c_data);
        return $this->db->insert_id();
		
		
	}
	function get_customers(){
		
           $query = $this->db->get('customer');
 
        if ($query->num_rows() > 0) {
            foreach ($query->result() as $row) {
                $data[] = $row;
            }
            return $data;
        }
        return false;
   }
    public function delete($id){
        $this->db->where('id', $id);
        $this->db->delete('customer');
    }
	
	public function get_by_id($id){
        $this->db->where('id', $id);
        return $this->db->get('customer');

    }
	public function update($id, $data){
        $this->db->where('id', $id);
        $this->db->update('customer', $data);
    }
		

	
}