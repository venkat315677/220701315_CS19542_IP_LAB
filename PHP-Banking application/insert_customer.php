<form method="post" action="insert_customer.php">
    Customer ID: <input type="number" name="cid" required><br>
    Customer Name: <input type="text" name="cname" required><br>
    <input type="submit" name="submit" value="Add Customer">
</form>

<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $cid = $_POST['cid'];
    $cname = $_POST['cname'];

    include 'connect_db.php';

    $sql = "INSERT INTO CUSTOMER (CID, CNAME) VALUES ('$cid', '$cname')";

    if ($conn->query($sql) === TRUE) {
        echo "New customer added successfully";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }

    $conn->close();
}
?>
