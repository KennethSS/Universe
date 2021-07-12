## Usage





### Activity (ViewBinding)

```kotlin
class MainActivity : UniverseViewActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvSample.text = "HI"
    }
}
```



### Fragment (ViewBinding)

```kotlin
class SampleViewFragment : UniverseViewFragment<FragmentMainBinding>(
  R.layout.fragment_main,
  FragmentMainBinding::bind) {
    override fun onViewCreated(bind: FragmentMainBinding, savedInstanceState: Bundle?) {

    }
}
```



### RecyclerView (ViewBinding)

```kotlin
class SampleAdapter : UniverseRecyclerViewAdapter() {

  override fun getHolder(viewType: Int, view: View): UniverseViewHolder = when (viewType) {
    R.layout.item_some -> SampleViewHolder(ItemSomeBinding.bind(view))
    else -> OtherHolder(ItemOtherBinding.bind(view))
  }

  override fun bind(holder: UniverseViewHolder, item: UniverseItemModel) = when(holder){
    is SampleViewHolder -> holder.bind(item as SomeModel)
    is OtherHolder -> holder.bind(item as OtherModel)
    else -> { }
  }

  override fun getItemViewType(position: Int): Int = when(list[position]) {
    is SomeModel -> R.layout.item_some
    else -> R.layout.item_other
  }
}

class SampleViewHolder(
  private val binding: ItemSomeBinding
) : UniverseViewHolder(binding.root) {

  fun bind(item: SomeModel) {
    binding.tvTitle.text = item.title
  }
}

data class SomeModel(
  val title: String
) : UniverseItemModel
```

