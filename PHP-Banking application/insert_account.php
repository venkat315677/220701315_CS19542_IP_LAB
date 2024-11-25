<form method="post" action="insert_account.php">
    Account Number: <input type="number" name="ano" required><br>
    Account Type (S or C): <input type="text" name="atype" maxlength="1" required><br>
    Balance: <input type="number" name="balance" required><br>
    Customer ID: <input type="number" name="cid" required><br>
    <input type="submit" name="submit" value="Add Account">
</form>

<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $ano = $_POST['ano'];
    $atype = $_POST['atype'];
    $balance = $_POST['balance'];
    $cid = $_POST['cid'];

    include 'connect_db.php';

    $sql = "INSERT INTO ACCOUNT (ANO, ATYPE, BALANCE, CID) VALUES ('$ano', '$atype', '$balance', '$cid')";

    if ($conn->query($sql) === TRUE) {
        echo "New account added successfully";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }

    $conn->close();
}
?>
