<?php

if($_SERVER['REQUEST_METHOD']=='POST'){

  $name=$_POST['name'];
  $password=$_POST['password'];
  $language=$_POST['app_language'];
  $version=$_POST['app_version'];

  $password=password_hash($password,PASSWORD_DEFAULT);

  require_once 'connect.php';

  $sql_exists_usr= "SELECT * FROM users_table WHERE name='$name'";

  $response = mysqli_query($conn, $sql_exists_usr);

  if(mysqli_num_rows($response)===1){
    $result["success"]="2";
    $result["message"]="existing";

    echo json_encode($result);
    mysqli_close($conn);
  }
  else{
    $sql ="INSERT INTO users_table(name, password, app_language, app_version) VALUES ('$name','$password','$language','$version')";

    if(mysqli_query($conn, $sql)){
      $result["success"]="1";
      $result["message"]="success";

      echo json_encode($result);
      mysqli_close($conn);
    } else{
      $result["success"]="0";
      $result["message"]="error";

      echo json_encode($result);
      mysqli_close($conn);
    }
  }

}
?>
