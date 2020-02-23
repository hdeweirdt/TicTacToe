package be.harm.deweirdt.domain

import java.lang.IllegalArgumentException
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FieldTest {

    private lateinit var field: Field

    @Before
    fun setUp() {
        field = Field()
    }

    @Test
    fun `a field is created as an empty field by default`() {
        assertEquals('.', field.symbol)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `overwriting a non-empty symbol results in an error`() {
        // Arrange
        field.symbol = 'O'

        // Act
        field.symbol = 'O'
    }
}
