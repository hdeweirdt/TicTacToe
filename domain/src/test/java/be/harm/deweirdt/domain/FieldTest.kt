package be.harm.deweirdt.domain

import be.harm.deweirdt.domain.game.Field
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
}
