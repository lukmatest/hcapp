package com.lukma.hcapplication.data.common

class MissingFieldException(field: String) : Exception("missing field : $field")