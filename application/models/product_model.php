<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Product_model extends CI_Model {

	public function __construct()
	{
		parent::__construct();
	}
	function get_products($per_page = 15, $curr_page_num = 0){
		
		$this->db->select('*');
		$this->db->from('efound_products');
		$this->db->limit($per_page, $curr_page_num);
		$query = $this->db->get();
		
		if ($query->num_rows() > 0) {
			foreach ($query->result() as $row) {
				$data[] = $row;
			}
			return $data;
		}
		return false;
    }

	function save($c_data){
        $this->db->insert('efound_products', $c_data);
        return $this->db->insert_id();
	}
    public function delete($id){
        $this->db->where('efpr_id', $id);
        $this->db->delete('efound_products');
    }
	
	public function get_by_id($id){
        $this->db->where('efpr_id', $id);
        return $this->db->get('efound_products');

    }
	public function update($id, $data){
        $this->db->where('efpr_id', $id);
        $this->db->update('efound_products', $data);
    }
		

	
}